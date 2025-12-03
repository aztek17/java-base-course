package ru.test.tireservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.tireservice.model.TyreService;

@Repository
public interface ServiceRepository extends JpaRepository<TyreService, Long> {
}