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
import ru.test.tireservice.dto.ServiceDtoRequest;
import ru.test.tireservice.dto.ServiceDtoResponse;
import ru.test.tireservice.service.ServicesService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Управление услугами", description = "Методы API для работы с услугами шиномонтажа")
public class ServiceController {

    private final ServicesService service;

    @Operation(
            summary = "Создание услуги",
            description = "Создаёт новую услугу в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Услуга успешно создана"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @PostMapping("/service")
    public ResponseEntity<ServiceDtoResponse> createService(@RequestBody ServiceDtoRequest dtoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createService(dtoRequest));
    }

    @Operation(
            summary = "Получение списка всех услуг",
            description = "Возвращает полный список всех услуг шиномонтажа в системе"
    )
    @ApiResponse(responseCode = "200", description = "Список услуг успешно получен")
    @GetMapping("/services")
    public ResponseEntity<List<ServiceDtoResponse>> getServices() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllServices());
    }

    @Operation(
            summary = "Получить услугу по ID",
            description = "Возвращает информацию о слуге по идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Услуга найдена"),
            @ApiResponse(responseCode = "404", description = "Услуга не найдена", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @GetMapping("/service/{id}")
    public ResponseEntity<ServiceDtoResponse> getServiceById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getServiceById(id));
    }

    @Operation(
            summary = "Обновить данные услуге",
            description = "Обновляет информацию услуги по ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Данные по услуге успешно обновлены"),
            @ApiResponse(responseCode = "404", description = "Услуга с указанным ID не найдена", content = @Content(
                    schema = @Schema(hidden = true)
            )),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)))
    })
    @PutMapping("/service/{id}")
    public ResponseEntity<ServiceDtoResponse> updateService(
            @PathVariable("id") Long id,
            @RequestBody ServiceDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateService(id, dto));
    }

}
