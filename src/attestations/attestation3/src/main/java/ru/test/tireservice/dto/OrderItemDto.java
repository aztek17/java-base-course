package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;
    private Long orderId;
    private Long serviceId;
    private String serviceName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subtotal;

}
