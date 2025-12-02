package ru.test.tireservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.test.tireservice.dto.OrderDtoRequest;
import ru.test.tireservice.dto.OrderDtoResponse;
import ru.test.tireservice.dto.OrderItemDto;
import ru.test.tireservice.dto.OrderItemRequestDto;
import ru.test.tireservice.model.*;
import ru.test.tireservice.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ServiceRepository serviceRepository;
    private final OrderItemRepository orderItemRepository;

    public OrderDtoResponse getOrderById(Long id) {
        Order order = orderRepository.findActiveById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Заказа с ID " + id + " не существует"
                ));
        return toDto(order);
    }

    public List<OrderDtoResponse> getAllOrders() {
        return orderRepository.findAllActive().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDtoResponse createOrder(OrderDtoRequest request) {
        User customer = userRepository.findActiveById(request.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Пользователя с ID " + request.getCustomerId() + " не существует"
                ));

        Car car = carRepository.findById(request.getCarId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Автомобиля с ID " + request.getCarId() + " не существует"
                ));

        Order order = Order.builder()
                .customer(customer)
                .car(car)
                .status(Order.OrderStatus.NEW)
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequestDto itemReq : request.getItems()) {
            TyreService service = serviceRepository.findById(itemReq.getServiceId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Услуги с ID " + itemReq.getServiceId() + " не существует"
                    ));

            OrderItem item = OrderItem.builder()
                    .order(savedOrder)
                    .service(service)
                    .quantity(itemReq.getQuantity())
                    .price(service.getPrice())
                    .build();

            orderItemRepository.save(item);
            total = total.add(service.getPrice()
                    .multiply(BigDecimal.valueOf(itemReq.getQuantity())));
        }

        savedOrder.setTotalAmount(total);
        orderRepository.save(savedOrder);

        return toDto(savedOrder);
    }

    @Transactional
    public OrderDtoResponse updateStatus(Long id, Order.OrderStatus status) {
        Order order = orderRepository.findActiveById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Заказа с ID " + id + " не существует"
                ));

        order.setStatus(status);
        if (status == Order.OrderStatus.COMPLETED) {
            order.setCompletedAt(LocalDateTime.now());
        }

        Order updatedOrder = orderRepository.save(order);
        return toDto(updatedOrder);
    }

    @Transactional
    public OrderDtoResponse addItemToOrder(Long orderId, OrderItemRequestDto itemReq) {
        Order order = orderRepository.findActiveById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Заказа с ID " + orderId + " не существует"
                ));

        if (order.getStatus() != Order.OrderStatus.NEW) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Можно добавлять услуги только в заказы со статусом NEW"
            );
        }

        TyreService service = serviceRepository.findById(itemReq.getServiceId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Услуги с ID " + itemReq.getServiceId() + " не существует"
                ));

        OrderItem item = OrderItem.builder()
                .service(service)
                .quantity(itemReq.getQuantity())
                .price(service.getPrice())
                .build();

        order.addItem(item);
        recalculateTotal(orderId);

        orderRepository.save(order);

        return toDto(orderRepository.findActiveById(orderId).get());
    }

    public List<OrderDtoResponse> getOrdersByCustomer(Long customerId) {
        User customer = userRepository.findActiveById(customerId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Пользователя с ID " + customerId + " не существует"
                ));

        return orderRepository.findByCustomer(customer).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<OrderDtoResponse> getOrdersByStatus(Order.OrderStatus status) {
        return orderRepository.findByStatus(status).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteOrderItem(Long orderId, Long itemId) {
        Order order = orderRepository.findActiveById(orderId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Заказа с ID " + orderId + " не существует"
                ));

        if (order.getStatus() != Order.OrderStatus.NEW) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Можно удалять услуги только из заказов со статусом NEW"
            );
        }

        OrderItem item = orderItemRepository.findById(itemId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Позиции заказа с ID " + itemId + " не существует"
                ));

        if (!item.getOrder().getId().equals(orderId)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Позиция не принадлежит указанному заказу"
            );
        }

        orderItemRepository.delete(item);
        recalculateTotal(orderId);
    }

    private void recalculateTotal(Long orderId) {
        List<OrderItem> items = orderItemRepository.findByOrderId(orderId);

        BigDecimal total = items.stream()
                .map(item -> item.getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Order order = orderRepository.findActiveById(orderId).get();
        order.setTotalAmount(total);
        orderRepository.save(order);
    }

    private OrderDtoResponse toDto(Order order) {
        List<OrderItemDto> listItemDto = orderItemRepository.findByOrderId(order.getId()).stream()
                .map(item -> OrderItemDto.builder()
                        .id(item.getId())
                        .serviceId(item.getService().getId())
                        .serviceName(item.getService().getServiceName())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .subtotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .build())
                .collect(Collectors.toList());

        String carInfo = String.format("%s %s)",
                order.getCar().getBrand(),
                order.getCar().getModel());

        return OrderDtoResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .customerName(order.getCustomer().getName())
                .carId(order.getCar().getId())
                .carInfo(carInfo)
                .createdAt(order.getCreatedAt())
                .items(listItemDto)
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .build();
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = orderRepository.findActiveById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Заказа с ID " + id + " не существует или он удален"
                ));

        if (order.getStatus() == Order.OrderStatus.IN_PROGRESS) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Нельзя удалять заказ в статусе IN_PROGRESS"
            );
        }

        orderRepository.softDelete(id);
    }
}
