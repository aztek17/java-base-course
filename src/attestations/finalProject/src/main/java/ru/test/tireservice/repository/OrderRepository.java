package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.isDeleted = false")
    List<Order> findAllActive();

    @Query("SELECT o FROM Order o WHERE o.id = :id AND o.isDeleted = false")
    Optional<Order> findActiveById(@Param("id") Long id);

    @Modifying
    @Query("UPDATE Order o SET o.isDeleted = true WHERE o.id = :id")
    void softDelete(@Param("id") Long id);

    List<Order> findByCustomer(User customer);

    List<Order> findByStatus(Order.OrderStatus status);
}
