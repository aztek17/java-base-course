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
public class UserDtoRequest {
    private String gender;
    private String name;
    private Integer age;
    private String email;
    private String phone;
    private List<Long> cars = new ArrayList<>();
//    private List<Long> customerOrders = new ArrayList<>();
//    private List<Order> customerOrders = new ArrayList<>();
//    private List<Order> masterOrders = new ArrayList<>();

    public static UserDtoRequest from(User user) {
        return UserDtoRequest.builder()
                .gender(user.getGender())
                .name(user.getName())
                .age(user.getAge())
                .email(user.getEmail())
                .phone(user.getPhone())
                .cars(user.getCars() != null
                        ? user.getCars().stream().map(Car::getId).toList()
                        : Collections.emptyList())
                .build();
    }

    public static List<UserDtoRequest> from(List<User> users) {
        return users.stream().map(UserDtoRequest::from).collect(Collectors.toList());
    }

    public static User to(UserDtoRequest dto) {
        return User.builder()
                .gender(dto.getGender())
                .name(dto.getName())
                .age(dto.getAge())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .build();
    }
}
