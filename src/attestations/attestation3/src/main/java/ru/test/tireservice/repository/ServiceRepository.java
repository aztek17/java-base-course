package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.tireservice.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}