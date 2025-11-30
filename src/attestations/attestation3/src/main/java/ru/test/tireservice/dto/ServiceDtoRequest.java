package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.model.Services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDtoRequest {

    private String serviceName;
    private String description;
    private BigDecimal price;
    private Integer durationMinutes;
    private Car.CarType carType;

    public static ServiceDtoRequest from(Services service) {
        return ServiceDtoRequest.builder()
                .serviceName(service.getServiceName())
                .description(service.getDescription())
                .price(service.getPrice())
                .durationMinutes(service.getDurationMinutes())
                .carType(service.getCarType())
                .build();
    }

    public static List<ServiceDtoRequest> from(List<Services> services) {
        return services.stream().map(ServiceDtoRequest::from).collect(Collectors.toList());
    }

    public static Services to(ServiceDtoRequest dto) {
        return Services.builder()
                .serviceName(dto.getServiceName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .durationMinutes(dto.getDurationMinutes())
                .carType(dto.getCarType())
                .build();
    }
}
