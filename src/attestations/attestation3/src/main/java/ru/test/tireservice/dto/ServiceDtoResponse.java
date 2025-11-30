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
public class ServiceDtoResponse {

    private Long id;
    private String serviceName;
    private String description;
    private BigDecimal price;
    private Integer durationMinutes;
    private Car.CarType carType;

    public static ServiceDtoResponse from(Services service) {
        return ServiceDtoResponse.builder()
                .id(service.getId())
                .serviceName(service.getServiceName())
                .description(service.getDescription())
                .price(service.getPrice())
                .durationMinutes(service.getDurationMinutes())
                .carType(service.getCarType())
                .build();
    }

    public static List<ServiceDtoResponse> from(List<Services> services) {
        return services.stream().map(ServiceDtoResponse::from).collect(Collectors.toList());
    }
}
