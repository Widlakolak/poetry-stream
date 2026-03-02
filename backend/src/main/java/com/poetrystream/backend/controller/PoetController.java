package com.poetrystream.backend.controller;

import com.poetrystream.backend.dto.PoetDto;
import com.poetrystream.backend.service.PoetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poets")
@RequiredArgsConstructor
public class PoetController {

    private final PoetService service;

    @GetMapping
    public List<PoetDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PoetDto getById(@PathVariable String id) {
        return service.getById(id);
    }

    @PostMapping
    public PoetDto create(@RequestBody PoetDto dto) {
        return service.create(dto);
    }
}