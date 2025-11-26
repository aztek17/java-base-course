package ru.demo.tv.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.demo.tv.dto.TvDtoRequest;
import ru.demo.tv.dto.TvDtoResponse;
import ru.demo.tv.service.TvService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Swagger TV", description = "Описание доступных методов приложения TV")
public class TvController {

    private final TvService service;

    @PostMapping("/tv")
    @Operation(summary = "Создание телевизора", description = "Метод для создания одного телевизора")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Успешное создание телевизора"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    public ResponseEntity<TvDtoResponse> createTv(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
                    description = "Параметры телевизора")
            @RequestBody TvDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createTv(dto));
    }

    @GetMapping("/tv")
    @Operation(summary = "Получение списка телевизоров",
            description = "Метод для получения списка всех телевизоров")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список телевизоров получен"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    public ResponseEntity<List<TvDtoResponse>> getTvs() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllTvs());
    }

    @GetMapping("/tv/{id}")
    @Operation(summary = "Получение телевизора по ID",
            description = "Метод для получения телевизора по уникальному идентификатору")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Телевизор получен"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            )),
            @ApiResponse(responseCode = "404", description = "Телевизор с таким ID не найден")
    })
    public ResponseEntity<TvDtoResponse> getTvById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getTvById(id));
    }

    @GetMapping("/tv/find")
    @Operation(summary = "Список телевизоров по модели",
            description = "Метод для получения списка телевизров по названию модели")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список телевизоров получен"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    public ResponseEntity<List<TvDtoResponse>> getTvsByBrand(
            @Parameter(name = "brand", required = true, description = "Название модели телевизора")
            @RequestParam(name = "brand") String brand) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getTvByBrand(brand));
    }

    @PutMapping("/tv/{id}")
    @Operation(summary = "Редактирование телевизора",
            description = "Метод для редактирования одного телевизора по уникальному идентификатору")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Телевизор изменен успешно"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            )),
            @ApiResponse(responseCode = "404", description = "Телевизор с таким ID не найден")
    })
    public ResponseEntity<TvDtoResponse> updateTv(
            @PathVariable("id") Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Параметры телевизора")
            @RequestBody TvDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateTv(id, dto));
    }

    @DeleteMapping("/tv/{id}")
    @Operation(summary = "Удаление телевизора",
            description = "Метод для удаления одного телевизора по идентификатору")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Телевизор удален"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            )),
            @ApiResponse(responseCode = "404", description = "Телевизор с таким ID не найден")
    })
    public ResponseEntity<TvDtoResponse> deleteTv(@PathVariable("id") Long id) {
        service.deleteTv(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
