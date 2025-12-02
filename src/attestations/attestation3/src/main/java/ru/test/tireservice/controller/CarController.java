package ru.test.tireservice.controller;

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
public class CarController {
    
    private final CarService service;

    @PostMapping("/car")
    public ResponseEntity<CarDtoResponse> createCar(@RequestBody CarDtoRequest dtoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createCar(dtoRequest));
    }

    @GetMapping("/cars")
    public ResponseEntity<List<CarDtoResponse>> getCars() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllCars());
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<CarDtoResponse> getCarById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getCarById(id));
    }

    @PutMapping("/car/{id}")
    public ResponseEntity<CarDtoResponse> updateCar(
            @PathVariable("id") Long id,
            @RequestBody CarDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateCar(id, dto));
    }
    
}
