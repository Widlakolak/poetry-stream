package com.poetrystream.backend.controller;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.repository.RecordingRepository;
import com.poetrystream.backend.service.RecordingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<RecordingDto> createRecording(
            @Valid @RequestBody Recording recording,
            UriComponentsBuilder uriBuilder) {

        Recording saved = service.save(recording);
        RecordingDto dto = service.mapToDto(saved);

        URI location = uriBuilder
                .path("/api/recordings/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(dto);
    }
}