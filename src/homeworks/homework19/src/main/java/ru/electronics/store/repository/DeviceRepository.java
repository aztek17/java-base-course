package ru.electronics.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.electronics.store.model.Device;

public interface DeviceRepository extends JpaRepository<Device, Long> {
}
