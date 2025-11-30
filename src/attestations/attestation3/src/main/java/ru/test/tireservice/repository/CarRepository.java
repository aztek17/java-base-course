package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.tireservice.model.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}