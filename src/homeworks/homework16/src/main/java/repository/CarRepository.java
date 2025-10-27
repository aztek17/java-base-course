package repository;

import car.Car;

import java.util.List;

public interface CarRepository {

    Car findByModel(String model);

    List<Car> findAll();

}
