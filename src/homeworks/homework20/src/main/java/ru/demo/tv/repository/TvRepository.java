package ru.demo.tv.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.demo.tv.model.Tv;

import java.util.List;

@Repository
public interface TvRepository extends JpaRepository<Tv, Long> {
    List<Tv> findByModel(String model);
}
