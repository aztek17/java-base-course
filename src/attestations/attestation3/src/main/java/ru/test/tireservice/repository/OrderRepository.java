package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.model.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomer(User customer);

    List<Order> findByStatus(Order.OrderStatus status);
}
