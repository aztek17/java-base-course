package repository;

import car.Car;

import java.util.List;

public interface CarRepository {
    void create(Car car);

    Car findByModel(String model);

    List<Car> findAll();

    void update(Car car);

    void deleteByModel(String model);

    void deleteAll();
}
