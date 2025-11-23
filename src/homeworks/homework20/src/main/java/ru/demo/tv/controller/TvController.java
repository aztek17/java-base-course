package ru.demo.tv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import ru.demo.tv.service.TvService;

@Controller
@RequiredArgsConstructor
public class TvController {

    private final TvService service;
}
