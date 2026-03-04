package com.poetrystream.backend.controller;

import com.poetrystream.backend.dto.PoemDto;
import com.poetrystream.backend.service.PoemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poems")
@RequiredArgsConstructor
public class PoemController {

    private final PoemService service;

    @GetMapping
    public List<PoemDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PoemDto getById(@PathVariable String id) {
        return service.getById(id);
    }

//    @PostMapping
//    public PoemDto create(@RequestBody PoemDto dto) {
//        return service.create(dto);
//    }
}