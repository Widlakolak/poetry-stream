package com.poetrystream.backend.controller;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.dto.RecordingKaraokeDto;
import com.poetrystream.backend.mapper.RecordingMapper;
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
    private final RecordingMapper mapper;

    @GetMapping
    public List<RecordingDto> getAll() {
        return service.getAllRecordings();
    }

    @GetMapping("/{id}")
    public RecordingDto getById(@PathVariable String id) {
        return service.getRecordingById(id);
    }

    @PostMapping
    public ResponseEntity<RecordingDto> createRecording(
            @Valid @RequestBody RecordingDto dto,
            UriComponentsBuilder uriBuilder) {

        Recording recording = Recording.builder()
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .actor(dto.getActor())
                .audioUrl(dto.getAudioUrl())
                .startTimeSec(dto.getStartTimeSec())
                // status ustawi się na DRAFT w @PrePersist
                .build();

        Recording saved = service.save(recording);

        RecordingDto response = mapper.toDto(saved);

        URI location = uriBuilder.path("/api/recordings/{id}")
                .buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}/karaoke")
    public ResponseEntity<RecordingKaraokeDto> getKaraoke(@PathVariable String id) {
        return ResponseEntity.ok(service.getKaraoke(id));
    }
}