package ru.electronics.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.electronics.store.model.Device;
import ru.electronics.store.service.DeviceService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class DeviceController {

    private final DeviceService service;

    @GetMapping("/devices")
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(service.getAllDevice());
    }

    @GetMapping("/device/{id}")
    public ResponseEntity<Optional<Device>> getDevice(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getDeviceById(id));
    }

    @PostMapping("/device")
    public ResponseEntity<Void> createDevice(@RequestBody Device device) {
        service.addDevice(device);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/device/{id}")
    public ResponseEntity<Void> updateDevice(@PathVariable("id") Long id, @RequestBody Device device) {
        service.updateDevice(id, device);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/device/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
