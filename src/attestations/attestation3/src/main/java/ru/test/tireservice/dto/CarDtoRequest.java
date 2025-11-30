package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Car;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarDtoRequest {

    private String brand;
    private String model;
    private Car.CarType type;
    private Integer tireSize;

    public static CarDtoRequest from(Car car) {
        return CarDtoRequest.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .type(car.getType())
                .tireSize(car.getTireSize())
                .build();
    }

    public static List<CarDtoRequest> from(List<Car> cars) {
        return cars.stream().map(CarDtoRequest::from).collect(Collectors.toList());
    }

    public static Car to(CarDtoRequest dto) {
        return Car.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .type(dto.getType())
                .tireSize(dto.getTireSize())
                .build();
    }

}