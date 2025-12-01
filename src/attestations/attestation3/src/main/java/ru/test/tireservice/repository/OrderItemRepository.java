package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.tireservice.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
