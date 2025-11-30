package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.Car;
import ru.test.tireservice.model.Service;

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

    public static ServiceDtoRequest from(Service service) {
        return ServiceDtoRequest.builder()
                .serviceName(service.getServiceName())
                .description(service.getDescription())
                .price(service.getPrice())
                .durationMinutes(service.getDurationMinutes())
                .carType(service.getCarType())
                .build();
    }

    public static List<ServiceDtoRequest> from(List<Service> services) {
        return services.stream().map(ServiceDtoRequest::from).collect(Collectors.toList());
    }
}
