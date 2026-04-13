package com.poetrystream.backend.controller;

import com.poetrystream.backend.dto.ActorDto;
import com.poetrystream.backend.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actors")
@RequiredArgsConstructor
public class ActorController {

    private final ActorService service;

    @GetMapping
    public List<ActorDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ActorDto getById(@PathVariable String id) {
        return service.getById(id);
    }

//    @PostMapping
//    public ActorDto create(@RequestBody ActorDto dto) {
//        return service.create(dto);
//    }
}