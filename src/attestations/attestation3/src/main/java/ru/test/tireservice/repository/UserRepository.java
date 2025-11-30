package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.tireservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}