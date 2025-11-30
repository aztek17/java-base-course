package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.model.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoRequest {

    private BigDecimal totalAmount;
    private Order.OrderStatus status;
    private LocalDateTime createdAt;
    private Long customer;
    private Long master;
    private Long car;
    private List<Long> services = new ArrayList<>();

    public static OrderDtoRequest from(Order order) {
        return OrderDtoRequest.builder()
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .customer(order.getCustomer().getId())
                .master(order.getMaster().getId())
                .services(order.getServices().stream().map(Service::getId).toList())
                .build();
    }

    public static List<OrderDtoRequest> from(List<Order> orders) {
        return orders.stream().map(OrderDtoRequest::from).collect(Collectors.toList());
    }

}
