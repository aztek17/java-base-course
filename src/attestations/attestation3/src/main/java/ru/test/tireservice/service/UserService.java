package ru.test.tireservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.test.tireservice.dto.UserDtoRequest;
import ru.test.tireservice.dto.UserDtoResponse;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.model.User;
import ru.test.tireservice.repository.CarRepository;
import ru.test.tireservice.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public UserDtoResponse getUsersById(Long id) {
        return UserDtoResponse.from(userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Пользователя с ID " + id + " не существует или он удален")));
    }

    public List<UserDtoResponse> getAllUsers() {
        return UserDtoResponse.from(userRepository.findAll());
    }

    public UserDtoResponse createUser(UserDtoRequest dto) {
        User user = UserDtoRequest.to(dto);
        User savedUser = userRepository.save(user);
        return UserDtoResponse.from(savedUser);
    }

    public UserDtoResponse updateUser(Long id, UserDtoRequest dto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Пользователя с ID " + id + " не существует или он удален"));
        if (dto.getGender() != null) {
            user.setGender(dto.getGender());
        }
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getAge() != null) {
            user.setAge(dto.getAge());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getCars() != null && !dto.getCars().isEmpty()) {
            List<Car> cars = carRepository.findAllById(dto.getCars());
            user.setCars(cars);
            cars.forEach(car -> car.setUser(user));
        }
        userRepository.save(user);
        return UserDtoResponse.from(user);
    }


}
