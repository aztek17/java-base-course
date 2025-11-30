package ru.test.tireservice.controller;

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
public class ServiceController {

    private final ServicesService service;

    @PostMapping("/service")
    public ResponseEntity<ServiceDtoResponse> createService(@RequestBody ServiceDtoRequest dtoRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createService(dtoRequest));
    }

    @GetMapping("/services")
    public ResponseEntity<List<ServiceDtoResponse>> getServices() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllServices());
    }

    @GetMapping("/service/{id}")
    public ResponseEntity<ServiceDtoResponse> getServiceById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getServiceById(id));
    }

    @PutMapping("/service/{id}")
    public ResponseEntity<ServiceDtoResponse> updateService(
            @PathVariable("id") Long id,
            @RequestBody ServiceDtoRequest dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateService(id, dto));
    }

//    @DeleteMapping("/service/{id}")
//    public ResponseEntity<ServiceDtoResponse> deleteService(@PathVariable("id") Long id) {
//        service.deleteService(id);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }

}
