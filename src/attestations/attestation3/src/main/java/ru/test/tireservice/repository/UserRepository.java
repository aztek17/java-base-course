package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.tireservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}