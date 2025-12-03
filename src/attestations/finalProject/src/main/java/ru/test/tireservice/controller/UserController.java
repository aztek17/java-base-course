package ru.test.tireservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.test.tireservice.dto.UserDtoRequest;
import ru.test.tireservice.dto.UserDtoResponse;
import ru.test.tireservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDtoResponse> createUser(@RequestBody UserDtoRequest dtoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(dtoRequest));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDtoResponse>> getUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUsersById(id));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDtoResponse> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUser(id, dto));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDtoResponse> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
