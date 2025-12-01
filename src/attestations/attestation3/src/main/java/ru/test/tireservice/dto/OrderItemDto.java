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

//    public static OrderItemDto from(OrderItem items) {
//        return OrderItemDto.builder()
//                .id(items.getId())
//                .orderId(items.getOrder().getId())
//                .serviceId(items.getService().getId())
//                .serviceName(items.getService().getServiceName())
//                .quantity(items.getQuantity())
//                .price(items.getPrice())
//                .subtotal(items.getSubtotal())
//                .build();
//    }

}
