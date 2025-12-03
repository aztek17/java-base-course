package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoResponse {
    private Long id;
    private String gender;
    private String name;
    private Integer age;
    private String email;
    private String phone;
    private List<Long> cars = new ArrayList<>();
    private List<Long> customerOrders = new ArrayList<>();

    public static UserDtoResponse from(User user) {
        return UserDtoResponse.builder()
                .id(user.getId())
                .gender(user.getGender())
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .phone(user.getPhone())
                .cars(user.getCars() != null
                        ? user.getCars().stream().map(Car::getId).toList()
                        : Collections.emptyList())
                .customerOrders(user.getCustomerOrders() != null
                        ? user.getCustomerOrders().stream().map(Order::getId).toList()
                        : Collections.emptyList())
                .build();
    }

    public static List<UserDtoResponse> from(List<User> users) {
        return users.stream().map(UserDtoResponse::from).collect(Collectors.toList());
    }
}
