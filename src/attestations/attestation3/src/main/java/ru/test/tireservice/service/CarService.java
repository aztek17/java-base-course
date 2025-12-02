package ru.test.tireservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.test.tireservice.dto.CarDtoRequest;
import ru.test.tireservice.dto.CarDtoResponse;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.repository.CarRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository repository;

    public CarDtoResponse getCarById(Long id) {
        return CarDtoResponse.from(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Автомобиля с ID " + id + " не существует или он удален")));
    }

    public List<CarDtoResponse> getAllCars() {
        return CarDtoResponse.from(repository.findAll());
    }

    public CarDtoResponse createCar(CarDtoRequest dto) {
        Car car = CarDtoRequest.to(dto);
        Car savedCar = repository.save(car);
        return CarDtoResponse.from(savedCar);
    }

    public CarDtoResponse updateCar(Long id, CarDtoRequest dto) {
        Car car = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Автомобиля с ID " + id + " не существует или он удален"));
        if (dto.getType() != null) {
            car.setType(dto.getType());
        }
        if (dto.getBrand() != null) {
            car.setBrand(dto.getBrand());
        }
        if (dto.getModel() != null) {
            car.setModel(dto.getModel());
        }
        if (dto.getTireSize() != null) {
            car.setTireSize(dto.getTireSize());
        }
        repository.save(car);
        return CarDtoResponse.from(car);
    }

}
