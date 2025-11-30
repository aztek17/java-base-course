package ru.test.tireservice.dto;

import jakarta.persistence.*;
import lombok.*;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.model.Service;
import ru.test.tireservice.model.User;

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
//    private User customer;
//    private User master;
    private Long customer;
    private Long master;
//    private Car car;
    private Long car;
//    private List<Service> services = new ArrayList<>(); // Нужен baseServiceDto что бы портянку не отдавать
    private List<Service> services = new ArrayList<>();

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
