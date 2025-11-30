package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.model.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoRequest {
    private Long customer;
    private Long car;
    private Long master;
    private List<Long> services = new ArrayList<>();

    public static OrderDtoRequest from(Order order) {
        return OrderDtoRequest.builder()
                .customer(order.getCustomer().getId())
                .car(order.getCar().getId())
                .master(order.getMaster() != null ? order.getMaster().getId() : null)
                .services(order.getServices().stream()
                        .map(Services::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public static List<OrderDtoRequest> from(List<Order> orders) {
        return orders.stream()
                .map(OrderDtoRequest::from)
                .collect(Collectors.toList());
    }
}