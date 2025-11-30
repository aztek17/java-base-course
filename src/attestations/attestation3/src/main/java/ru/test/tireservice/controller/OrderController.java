package ru.test.tireservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.test.tireservice.dto.OrderDtoRequest;
import ru.test.tireservice.dto.OrderDtoResponse;
import ru.test.tireservice.model.Order;
import ru.test.tireservice.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    
    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<OrderDtoResponse> createOrder(@RequestBody OrderDtoRequest dtoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(dtoRequest));
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderDtoResponse>> getOrders() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDtoResponse> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<OrderDtoResponse> updateOrder(
            @PathVariable("id") Long id,
            @RequestBody OrderDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.updateOrder(id, dto));
    }

    @PutMapping("/order/{id}/status")
    public ResponseEntity<OrderDtoResponse> changeOrderStatus(
            @PathVariable("id") Long id,
            @RequestParam(name = "newStatus") Order.OrderStatus newStatus) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.updateOrderStatus(id, newStatus));
    }

//    @DeleteMapping("/order/{id}")
//    public ResponseEntity<OrderDtoResponse> deleteOrder(@PathVariable("id") Long id) {
//        orderService.deleteOrder(id);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
    
}
