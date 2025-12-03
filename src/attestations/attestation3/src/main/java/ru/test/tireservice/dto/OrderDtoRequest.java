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

}