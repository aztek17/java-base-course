package ru.test.tireservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.test.tireservice.dto.OrderDtoRequest;
import ru.test.tireservice.dto.OrderDtoResponse;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.model.Services;
import ru.test.tireservice.model.User;
import ru.test.tireservice.repository.CarRepository;
import ru.test.tireservice.repository.OrderRepository;
import ru.test.tireservice.repository.ServiceRepository;
import ru.test.tireservice.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final ServiceRepository serviceRepository;

    public OrderDtoResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Заказа с ID " + id + " не существует"));
        return OrderDtoResponse.from(order);
    }

    public List<OrderDtoResponse> getAllOrders() {
        return OrderDtoResponse.from(orderRepository.findAll());
    }

    public OrderDtoResponse createOrder(OrderDtoRequest dto) {
        User customer = userRepository.findById(dto.getCustomer())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Пользователя с ID " + dto.getCustomer() + " не существует"));

        Car car = carRepository.findById(dto.getCar())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Автомобиля с ID " + dto.getCar() + " не существует"));

        User master = null;
        if (dto.getMaster() != null) {
            master = userRepository.findById(dto.getMaster())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Мастера с ID " + dto.getMaster() + " не существует"));
        }

        List<Services> services = serviceRepository.findAllById(dto.getServices());
        if (services.size() != dto.getServices().size()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Некоторые услуги не найдены");
        }

        BigDecimal totalAmount = calculateTotalSum(services);

        Order order = Order.builder()
                .totalAmount(totalAmount)
                .status(Order.OrderStatus.NEW)
                .createdAt(LocalDateTime.now())
                .customer(customer)
                .master(master)
                .car(car)
                .services(services)
                .build();

        Order savedOrder = orderRepository.save(order);
        return OrderDtoResponse.from(savedOrder);
    }

    public OrderDtoResponse updateOrder(Long id, OrderDtoRequest dto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Заказа с ID " + id + " не существует"));

        if (dto.getCustomer() != null) {
            User customer = userRepository.findById(dto.getCustomer())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Пользователя с ID " + dto.getCustomer() + " не существует"));
            order.setCustomer(customer);
        }

        if (dto.getCar() != null) {
            Car car = carRepository.findById(dto.getCar())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Автомобиля с ID " + dto.getCar() + " не существует"));
            order.setCar(car);
        }

        if (dto.getMaster() != null) {
            User master = userRepository.findById(dto.getMaster())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Мастера с ID " + dto.getMaster() + " не существует"));
            order.setMaster(master);
        }

        if (dto.getServices() != null && !dto.getServices().isEmpty()) {
            List<Services> services = serviceRepository.findAllById(dto.getServices());
            if (services.size() != dto.getServices().size()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Некоторые услуги не найдены");
            }
            order.setServices(services);
            order.setTotalAmount(calculateTotalSum(services));
        }

        Order updatedOrder = orderRepository.save(order);
        return OrderDtoResponse.from(updatedOrder);
    }

    public OrderDtoResponse updateOrderStatus(Long id, Order.OrderStatus newStatus) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Заказа с ID " + id + " не существует"));

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);
        return OrderDtoResponse.from(updatedOrder);
    }

    public List<OrderDtoResponse> getOrdersByCustomer(Long customerId) {
        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Пользователя с ID " + customerId + " не существует"));

        return OrderDtoResponse.from(orderRepository.findByCustomer(customer));
    }

    public List<OrderDtoResponse> getOrdersByMaster(Long masterId) {
        User master = userRepository.findById(masterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Мастера с ID " + masterId + " не существует"));

        return OrderDtoResponse.from(orderRepository.findByMaster(master));
    }

    public List<OrderDtoResponse> getOrdersByStatus(Order.OrderStatus status) {
        return OrderDtoResponse.from(orderRepository.findByStatus(status));
    }

    private BigDecimal calculateTotalSum(List<Services> services) {
        BigDecimal total = BigDecimal.ZERO;
        for (Services service : services) {
            total = total.add(service.getPrice());
        }
        return total;
    }
}
