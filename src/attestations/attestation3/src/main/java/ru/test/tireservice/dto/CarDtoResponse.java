package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarDtoResponse {

    private Long id;
    private String brand;
    private String model;
    private Car.CarType type;
    private Integer tireSize;
    private Long userId;
    private List<Long> orders = new ArrayList<>();

    public static CarDtoResponse from(Car car) {
        return CarDtoResponse.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .type(car.getType())
                .tireSize(car.getTireSize())
                .userId(car.getUser().getId())
                .orders(car.getOrders().stream().map(Order::getId).toList())
                .build();
    }

    public static List<CarDtoResponse> from(List<Car> cars) {
        return cars.stream().map(CarDtoResponse::from).collect(Collectors.toList());
    }

}