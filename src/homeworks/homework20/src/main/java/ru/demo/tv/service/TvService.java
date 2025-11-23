package ru.demo.tv.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.data.metrics.DefaultRepositoryTagsProvider;
import org.springframework.stereotype.Service;
import ru.demo.tv.dto.TvDto;
import ru.demo.tv.model.Tv;
import ru.demo.tv.repository.TvRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TvService {

    private final TvRepository repository;

    private final DefaultRepositoryTagsProvider repositoryTagsProvider;

    public TvDto createTv(TvDto dto) {
        Tv tv = Tv.builder()
                .brand(dto.getBrand())
                .model(dto.getModel())
                .price(dto.getPrice())
                .voiceAssistant(dto.isVoiceAssistant())
                .screenSize(dto.getScreenSize())
                .build();

        Tv savedTv = repository.save(tv);
        return TvDto.from(savedTv);
    }

    public TvDto getTvById(Long id) {
        return TvDto.from(repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Телевизор с ID: " + id + " не найден!")));
    }

    public List<TvDto> getTvByModel(String model) {
        return TvDto.from(repository.findByModel(model));
    }

    public List<TvDto> getAllTvs() {
        return TvDto.from(repository.findAll());
    }

    public TvDto updateTv(Long id, TvDto dto) {
        Tv tv = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Телевизор с ID: " + id + " не найден!"));

        tv.setModel(dto.getModel());
        tv.setBrand(dto.getBrand());
        tv.setPrice(dto.getPrice());
        tv.setVoiceAssistant(dto.isVoiceAssistant());
        tv.setScreenSize(dto.getScreenSize());

        repository.save(tv);
        return TvDto.from(tv);
    }

    public void deleteTv(Long id) {
        Tv tv = repository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Телевизор с ID: " + id + " не найден!"));
        repository.delete(tv);
    }
}
