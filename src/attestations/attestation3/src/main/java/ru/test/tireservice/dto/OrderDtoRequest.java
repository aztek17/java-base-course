package ru.test.tireservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoRequest {
    @NotNull
    private Long customerId;
    @NotNull
    private Long carId;
    private List<OrderItemRequestDto> items = new ArrayList<>();

//    public static OrderDtoRequest from(Order order) {
//        return OrderDtoRequest.builder()
//                .customer(order.getCustomer().getId())
//                .car(order.getCar().getId())
//                .items(order.getItems())
//                .build();
//    }
//
//    public static List<OrderDtoRequest> from(List<Order> orders) {
//        return orders.stream()
//                .map(OrderDtoRequest::from)
//                .collect(Collectors.toList());
//    }
}