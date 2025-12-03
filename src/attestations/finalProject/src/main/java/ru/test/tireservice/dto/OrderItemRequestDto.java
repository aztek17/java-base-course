package ru.test.tireservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDto {
    @NotNull(message = "Поле ID услуги не может быть пустым")
    private Long serviceId;

    private Integer quantity = 1;
}
