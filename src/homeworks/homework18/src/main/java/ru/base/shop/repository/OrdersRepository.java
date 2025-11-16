package ru.base.shop.repository;

import ru.base.shop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    Orders findByOrderDate(LocalDate orderDate);
}
