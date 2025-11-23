package ru.electronics.store.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.electronics.store.model.Device;
import ru.electronics.store.repository.DeviceRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository repository;

    public List<Device> getAllDevice() {
        return repository.findAll();
    }

    public void addDevice(Device device) {
        repository.save(device);
    }

    public Optional<Device> getDeviceById(Long id) {
        return repository.findById(id);
    }

    public void updateDevice(Long id, Device device) {
        Device targetDevice = repository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("!!! Device not found !!!"));

        targetDevice.setType(device.getType());
        targetDevice.setBrand(device.getBrand());
        targetDevice.setModelName(device.getModelName());
        targetDevice.setPrice(device.getPrice());

        repository.save(targetDevice);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
