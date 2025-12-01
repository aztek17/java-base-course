package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.tireservice.model.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
