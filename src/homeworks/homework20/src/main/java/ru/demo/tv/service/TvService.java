package ru.demo.tv.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.demo.tv.repository.TvRepository;

@Service
@RequiredArgsConstructor
public class TvService {

    private final TvRepository repository;

}
