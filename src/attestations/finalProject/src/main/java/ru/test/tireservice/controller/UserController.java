package ru.test.tireservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.tireservice.dto.UserDtoRequest;
import ru.test.tireservice.dto.UserDtoResponse;
import ru.test.tireservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Управление пользователями", description = "Методы API для работы с пользователями")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Создание пользователя",
            description = "Создаёт нового пользователя в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Пользователь успешно создана"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @PostMapping("/user")
    public ResponseEntity<UserDtoResponse> createUser(@RequestBody UserDtoRequest dtoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createUser(dtoRequest));
    }

    @Operation(
            summary = "Получение списка всех пользователей",
            description = "Возвращает полный список всех пользователей шиномонтажа в системе"
    )
    @ApiResponse(responseCode = "200", description = "Список пользователей успешно получен")
    @GetMapping("/users")
    public ResponseEntity<List<UserDtoResponse>> getUsers() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getAllUsers());
    }

    @Operation(
            summary = "Получить пользователя по ID",
            description = "Возвращает информацию о пользователе по идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.getUsersById(id));
    }

    @Operation(
            summary = "Обновить данные пользователя",
            description = "Обновляет информацию пользователя по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Данные по пользователю успешно обновлены"),
            @ApiResponse(responseCode = "404", description = "Пользователь с указанным ID не найден", content = @Content(
                    schema = @Schema(hidden = true)
            )),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)))
    })
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDtoResponse> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userService.updateUser(id, dto));
    }

    @Operation(
            summary = "Удалить пользователя по ID(soft удаление)",
            description = "Удаляет пользователя по ID(помечает признаком в БД и исключает для отдачи в ответе клиенту)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказ найден"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @DeleteMapping("/user/{id}")
    public ResponseEntity<UserDtoResponse> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
