package ru.demo.tv.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.demo.tv.model.Tv;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TvDtoRequest {

    private String brand;
    private String model;
    private Integer price;
    private boolean voiceAssistant;
    private Integer screenSize;

    public static TvDtoRequest from(Tv tv) {
        return TvDtoRequest.builder()
                .brand(tv.getBrand())
                .model(tv.getModel())
                .price(tv.getPrice())
                .voiceAssistant(tv.isVoiceAssistant())
                .screenSize(tv.getScreenSize())
                .build();
    }

    public static List<TvDtoRequest> from(List<Tv> tvs) {
        return tvs.stream().map(TvDtoRequest::from).collect(Collectors.toList());
    }

}
