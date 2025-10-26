package repository.impl;

import car.Car;
import repository.CarRepository;

import java.util.List;

public class CarRepositoryImpl implements CarRepository {
    @Override
    public void create(Car car) {
        
    }

    @Override
    public Car findByModel(String model) {
        return null;
    }

    @Override
    public List<Car> findAll() {
        return List.of();
    }

    @Override
    public void update(Car car) {

    }

    @Override
    public void deleteByModel(String model) {

    }

    @Override
    public void deleteAll() {

    }
}
