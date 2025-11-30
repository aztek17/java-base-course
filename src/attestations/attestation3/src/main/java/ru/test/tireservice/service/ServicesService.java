package ru.test.tireservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.test.tireservice.dto.ServiceDtoRequest;
import ru.test.tireservice.dto.ServiceDtoResponse;
import ru.test.tireservice.model.Services;
import ru.test.tireservice.repository.ServiceRepository;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServicesService {

    private final ServiceRepository repository;

    public ServiceDtoResponse getServiceById(Long id) {
        return ServiceDtoResponse.from(repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Услуги с ID " + id + " не существует или он удалена")));
    }

    public List<ServiceDtoResponse> getAllServices() {
        return ServiceDtoResponse.from(repository.findAll());
    }

    public ServiceDtoResponse createService(ServiceDtoRequest dto) {
        Services service = ServiceDtoRequest.to(dto);
        Services savedService = repository.save(service);
        return ServiceDtoResponse.from(savedService);
    }

    public ServiceDtoResponse updateService(Long id, ServiceDtoRequest dto) {
        Services service = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Услуги с ID " + id + " не существует или он удалена"));
        if (dto.getServiceName() != null) {
            service.setServiceName(dto.getServiceName());
        }
        if (dto.getDescription() != null) {
            service.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            service.setPrice(dto.getPrice());
        }
        if (dto.getDurationMinutes() != null) {
            service.setDurationMinutes(dto.getDurationMinutes());
        }
        if (dto.getCarType() != null) {
            service.setCarType(dto.getCarType());
        }
        repository.save(service);
        return ServiceDtoResponse.from(service);
    }

//    public void deleteService(Long id) {
//        Service service = repository.findById(id).orElseThrow(()
//                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Услуги с ID " + id + " не существует или он удалена"));
//        service.setDeleted(true);
//        repository.save(service);
//    }

}
