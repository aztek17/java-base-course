package homeworks.homework11.repository;

import homeworks.homework11.model.Car;

import java.util.List;

public interface CarRepository {

    List<Car> findAll();

    void save(String text);
}
