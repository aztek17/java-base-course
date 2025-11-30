package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.model.User;

import java.util.ArrayList;
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
//    private Long userId;
    private List<Long> orders = new ArrayList<>();

    public static CarDtoRequest from(Car car) {
        return CarDtoRequest.builder()
                .brand(car.getBrand())
                .model(car.getModel())
                .type(car.getType())
                .tireSize(car.getTireSize())
//                .userId(car.getUser().getId())
                .build();
    }

    public static List<CarDtoRequest> from(List<Car> cars) {
        return cars.stream().map(CarDtoRequest::from).collect(Collectors.toList());
    }

}