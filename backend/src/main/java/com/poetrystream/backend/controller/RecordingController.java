package com.poetrystream.backend.controller;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.repository.RecordingRepository;
import com.poetrystream.backend.service.RecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recordings")
@RequiredArgsConstructor
public class RecordingController {

    private final RecordingService service;
    private final RecordingRepository repository;

    @GetMapping
    public List<RecordingDto> getAll() {
        return service.getAllRecordings();
    }

    @GetMapping("/{id}")
    public RecordingDto getById(@PathVariable String id) {
        return service.getRecordingById(id);
    }

    public RecordingDto getRecordingById(String id) {
        Recording recording = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recording not found"));

        return service.mapToDto(recording);
    }

    @PostMapping
    public Recording add(@RequestBody Recording recording) {
        return service.save(recording);
    }
}