package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoResponse {

    private Long id;
    private Long customerId;
    private String customerName;
    private Long carId;
    private String carInfo;
    private LocalDateTime createdAt;
    private List<OrderItemDto> items;
    private BigDecimal totalAmount;
    private Order.OrderStatus status;

    public Integer getTotalItemsCount() {
        return items != null ?
                items.stream().mapToInt(OrderItemDto::getQuantity).sum() : 0;
    }

    public String getServicesList() {
        if (items == null || items.isEmpty()) {
            return "Нет услуг";
        }
        return items.stream()
                .map(item -> item.getServiceName() + " x" + item.getQuantity())
                .collect(Collectors.joining(", "));
    }

}
