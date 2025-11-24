package ru.demo.tv.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.demo.tv.dto.TvDto;
import ru.demo.tv.service.TvService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class TvController {

    private final TvService service;

    @PostMapping("/tv")
    public ResponseEntity<TvDto> createTv(@RequestBody TvDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createTv(dto));
    }

    @GetMapping("/tv")
    public ResponseEntity<List<TvDto>> getTvs() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getAllTvs());
    }

    @GetMapping("/tv/{id}")
    public ResponseEntity<TvDto> getTvById(@PathVariable("id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getTvById(id));
    }

    @GetMapping("/tv/find")
    public ResponseEntity<List<TvDto>> getTvsByBrand(@RequestParam(name = "brand") String brand) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getTvByBrand(brand));
    }

    @PutMapping("/tv/{id}")
    public ResponseEntity<TvDto> updateTv(@PathVariable("id") Long id, @RequestBody TvDto dto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateTv(id, dto));
    }

    @DeleteMapping("/tv/{id}")
    public ResponseEntity<TvDto> deleteTv(@PathVariable("id") Long id) {
        service.deleteTv(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
