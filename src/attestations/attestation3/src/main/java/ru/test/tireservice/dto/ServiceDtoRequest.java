package ru.test.tireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.tireservice.model.TyreService;

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

    public static ServiceDtoRequest from(TyreService service) {
        return ServiceDtoRequest.builder()
                .serviceName(service.getServiceName())
                .description(service.getDescription())
                .price(service.getPrice())
                .durationMinutes(service.getDurationMinutes())
                .build();
    }

    public static List<ServiceDtoRequest> from(List<TyreService> services) {
        return services.stream().map(ServiceDtoRequest::from).collect(Collectors.toList());
    }

    public static TyreService to(ServiceDtoRequest dto) {
        return TyreService.builder()
                .serviceName(dto.getServiceName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .durationMinutes(dto.getDurationMinutes())
                .build();
    }
}
