package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.model.Services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoResponse {

    private Long id;
    private BigDecimal totalAmount;
    private Order.OrderStatus status;
    private LocalDateTime createdAt;
    private Long customer;
    private Long master;
    private Long car;
    private List<Services> services = new ArrayList<>(); // Нужен baseServiceDto что бы портянку не отдавать

    public static OrderDtoResponse from(Order order) {
        return OrderDtoResponse.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .customer(order.getCustomer().getId())
                .master(order.getMaster().getId())
                .services(order.getServices()) // Нужен baseServiceDto что бы портянку не отдавать
                .build();
    }

    public static List<OrderDtoResponse> from(List<Order> orders) {
        return orders.stream().map(OrderDtoResponse::from).collect(Collectors.toList());
    }

}
