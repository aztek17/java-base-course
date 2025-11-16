package ru.base.shop.model;

import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Entity
@Table(name = "orders", schema = "shop")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "count_order")
    private Integer countOrder;

    @Column(name = "discount")
    private Integer discount;

}
