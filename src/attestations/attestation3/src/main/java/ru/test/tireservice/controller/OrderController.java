package ru.test.tireservice.controller;

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
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDtoResponse> createOrder(@RequestBody OrderDtoRequest dtoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(dtoRequest));
    }

    @GetMapping
    public ResponseEntity<List<OrderDtoResponse>> getAllOrders() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoResponse> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDtoResponse> changeOrderStatus(
            @PathVariable("id") Long id,
            @RequestParam(name = "newStatus") Order.OrderStatus newStatus) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.updateStatus(id, newStatus));
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<OrderDtoResponse> addItemToOrder(@PathVariable Long id,
                                                           @Valid @RequestBody OrderItemRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.addItemToOrder(id, request));
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderItem(@PathVariable Long orderId,
                                @PathVariable Long itemId) {
        orderService.deleteOrderItem(orderId, itemId);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDtoResponse>> getCustomerOrders(@PathVariable Long customerId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrdersByCustomer(customerId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderDtoResponse>> getOrdersByStatus(@PathVariable Order.OrderStatus status) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrdersByStatus(status));
    }

//    @DeleteMapping("/order/{id}")
//    public ResponseEntity<OrderDtoResponse> deleteOrder(@PathVariable("id") Long id) {
//        orderService.deleteOrder(id);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

}
