package ru.base.shop.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "count_order")
    private Integer countOrder;

    @Column(name = "discount")
    private Integer discount;

}
