package ru.demo.tv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.demo.tv.dto.TvDtoRequest;
import ru.demo.tv.dto.TvDtoResponse;
import ru.demo.tv.model.Tv;
import ru.demo.tv.repository.TvRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TvService {

    private final TvRepository repository;

    public TvDtoResponse createTv(TvDtoRequest dto) {
        Tv tv = Tv.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .price(dto.getPrice())
                .voiceAssistant(dto.isVoiceAssistant())
                .screenSize(dto.getScreenSize())
                .build();

        Tv savedTv = repository.save(tv);
        return TvDtoResponse.from(savedTv);
    }

    public TvDtoResponse getTvById(Long id) {
        return TvDtoResponse.from(repository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Телевизор с ID: " + id + " не найден!")));
    }

    public List<TvDtoResponse> getTvByBrand(String brand) {
        return TvDtoResponse.from(repository.findByBrand(brand));
    }

    public List<TvDtoResponse> getAllTvs() {
        return TvDtoResponse.from(repository.findAll());
    }

    public TvDtoResponse updateTv(Long id, TvDtoRequest dto) {
        Tv tv = repository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Телевизор с ID: " + id + " не найден!"));

        tv.setModel(dto.getModel());
        tv.setBrand(dto.getBrand());
        tv.setPrice(dto.getPrice());
        tv.setVoiceAssistant(dto.isVoiceAssistant());
        tv.setScreenSize(dto.getScreenSize());

        repository.save(tv);
        return TvDtoResponse.from(tv);
    }

    public void deleteTv(Long id) {
        Tv tv = repository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Телевизор с ID: " + id + " не найден!"));
        repository.delete(tv);
    }
}
