package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.tireservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}