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
import ru.test.tireservice.dto.OrderDtoRequest;
import ru.test.tireservice.dto.OrderDtoResponse;
import ru.test.tireservice.dto.OrderItemRequestDto;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
@Tag(name = "Управление заказами", description = "Методы API для работы с заказами")
public class OrderController {

    private final OrderService orderService;

    @Operation(
            summary = "Создание заказа",
            description = "Создаёт заказ в системе"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Заказ успешно создан"),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @PostMapping
    public ResponseEntity<OrderDtoResponse> createOrder(@io.swagger.v3.oas.annotations.parameters.RequestBody(required = true,
            description = "Параметры автомобиля") @RequestBody OrderDtoRequest dtoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(dtoRequest));
    }

    @Operation(
            summary = "Получение списка всех заказов",
            description = "Возвращает полный список всех заказов"
    )
    @ApiResponse(responseCode = "200", description = "Список заказов успешно получен")
    @GetMapping
    public ResponseEntity<List<OrderDtoResponse>> getAllOrders() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @Operation(
            summary = "Получить заказ по ID",
            description = "Возвращает информацию о заказе по идентификатору"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказ найден"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> getOrderById(
            @Parameter(description = "Уникальный идентификатор автомобиля")
            @PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }

    @Operation(
            summary = "Изменить статус заказа",
            description = "Изменяет статус заказа"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Статус успешно обновлены"),
            @ApiResponse(responseCode = "404", description = "Заказ с указанным ID не найден", content = @Content(
                    schema = @Schema(hidden = true)
            )),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)))
    })
    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDtoResponse> changeOrderStatus(
            @Parameter(description = "Уникальный идентификатор заказа")
            @PathVariable("id") Long id,
            @Parameter(description = "Новый статус заказа")
            @RequestParam(name = "newStatus") Order.OrderStatus newStatus) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.updateStatus(id, newStatus));
    }

    @Operation(
            summary = "Добавить позицию в заказ",
            description = "Добавляет услугу в заказ"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Позиция добавлена в заказ"),
            @ApiResponse(responseCode = "404", description = "Заказ с указанным ID не найден", content = @Content(
                    schema = @Schema(hidden = true)
            )),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)))
    })
    @PostMapping("/{id}/items")
    public ResponseEntity<OrderDtoResponse> addItemToOrder(
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "Параметры позиции в заказе")
            @Valid @RequestBody OrderItemRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.addItemToOrder(id, request));
    }
    @Operation(
            summary = "Удалить позицию из заказа",
            description = "Удаляет услугу из заказа"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Позиция удалена из заказа"),
            @ApiResponse(responseCode = "404", description = "Заказ или позиция с указанным ID не найдены", content = @Content(
                    schema = @Schema(hidden = true)
            )),
            @ApiResponse(responseCode = "400", description = "Запрос составлен некорректно", content = @Content(
                    schema = @Schema(hidden = true)))
    })
    @DeleteMapping("/{orderId}/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderItem(@PathVariable Long orderId,
                                @PathVariable Long itemId) {
        orderService.deleteOrderItem(orderId, itemId);
    }

    @Operation(
            summary = "Получить заказы по ID покупателя",
            description = "Возвращает список заказов по ID покупателя"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказы найден"),
            @ApiResponse(responseCode = "404", description = "Покупатель не найден", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDtoResponse>> getCustomerOrders(@PathVariable Long customerId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrdersByCustomer(customerId));
    }

    @Operation(
            summary = "Получить заказы по статусу",
            description = "Возвращает список заказов по статусу"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказы найден"),
            @ApiResponse(responseCode = "404", description = "Статус не корректен", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderDtoResponse>> getOrdersByStatus(@PathVariable Order.OrderStatus status) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrdersByStatus(status));
    }

    @Operation(
            summary = "Удалить заказ по ID(soft удаление)",
            description = "Удаляет заказ по ID(помечает признаком в БД и исключает для отдачи в ответе клиенту)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Заказ найден"),
            @ApiResponse(responseCode = "404", description = "Заказ не найден", content = @Content(
                    schema = @Schema(hidden = true)
            ))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
