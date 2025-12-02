package ru.test.tireservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem extends BaseModel {

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private TyreService service;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private BigDecimal price;

    @PrePersist
    @PreUpdate
    public void prePersistAndUpdate() {
        if (quantity == null || quantity <= 0) {
            quantity = 1;
        }
    }

}
