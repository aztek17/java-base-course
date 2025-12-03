package ru.test.tireservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.tireservice.dto.CarDtoRequest;
import ru.test.tireservice.dto.CarDtoResponse;
import ru.test.tireservice.service.CarService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Управление автомобилями", description = "Методы API для работы с данными автомобилей")
public class CarController {

    private final CarService service;

    @Operation(
            summary = "Создание нового автомобиля",
            description = "Создаёт новую запись об автомобиле в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Автомобиль успешно создан"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @PostMapping("/car")
    public ResponseEntity<CarDtoResponse> createCar(@Valid @RequestBody CarDtoRequest dtoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createCar(dtoRequest));
    }

    @Operation(
            summary = "Получение списка всех автомобилей",
            description = "Возвращает полный список всех автомобилей, зарегистрированных в системе"
    )
    @ApiResponse(responseCode = "200", description = "Список автомобилей успешно получен")
    @GetMapping("/cars")
    public ResponseEntity<List<CarDtoResponse>> getCars() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllCars());
    }

    @Operation(
            summary = "Получить автомобиль по ID",
            description = "Возвращает информацию об автомобиле по идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Автомобиль найден"),
            @ApiResponse(responseCode = "404", description = "Автомобиль не найден", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDtoResponse> getCarById(
            @Parameter(description = "Уникальный идентификатор автомобиля")
            @PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getCarById(id));
    }

    @Operation(
            summary = "Обновить данные автомобиля",
            description = "Обновляет информацию об автомобиле по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Данные автомобиля успешно обновлены"),
            @ApiResponse(responseCode = "404", description = "Автомобиль с указанным ID не найден", content = @Content(
                    schema = @Schema(hidden = true)
            )),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)))
    })
    @PutMapping("/car/{id}")
    public ResponseEntity<CarDtoResponse> updateCar(
            @PathVariable("id") Long id,
            @RequestBody CarDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateCar(id, dto));
    }

}
