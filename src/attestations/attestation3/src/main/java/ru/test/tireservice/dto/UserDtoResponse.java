package ru.test.tireservice.dto;

import jakarta.persistence.*;
import lombok.*;
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
public class UserDtoResponse {
    private Long id;
    private String gender;
    private Integer age;
    private String email;
    private String phone;
    private List<Long> cars = new ArrayList<>();
    private List<Long> customerOrders = new ArrayList<>();
//    private List<Order> customerOrders = new ArrayList<>();
//    private List<Order> masterOrders = new ArrayList<>();

    public static UserDtoResponse from(User user) {
        return UserDtoResponse.builder()
                .id(user.getId())
                .gender(user.getGender())
                .age(user.getAge())
                .email(user.getEmail())
                .phone(user.getPhone())
                .cars(user.getCars().stream().map(Car::getId).toList())
                .customerOrders(user.getCustomerOrders().stream().map(Order::getId).toList())
//                .masterOrders(user.getMasterOrders()) // Выделить отдельно
                .build();
    }

    public static List<UserDtoResponse> from(List<User> users) {
        return users.stream().map(UserDtoResponse::from).collect(Collectors.toList());
    }
}
