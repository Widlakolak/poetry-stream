package com.poetrystream.backend.controller;

import com.poetrystream.backend.domain.RecordingStatus;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.dto.RecordingKaraokeDto;
import com.poetrystream.backend.service.RecordingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

@RestController
@RequestMapping("/api/recordings")
@RequiredArgsConstructor
public class RecordingController {

    private final RecordingService service;

    @GetMapping
    public Page<RecordingDto> getAll(
            @RequestParam(required = false) RecordingStatus status,
            Pageable pageable) {

        return service.getAll(status, pageable);
    }

    @GetMapping("/{id}")
    public RecordingDto getById(@PathVariable String id) {
        return service.getById(id);
    }

//    @PostMapping
//    public ResponseEntity<RecordingDto> create(
//            @Valid @RequestBody RecordingDto dto,
//            UriComponentsBuilder uriBuilder) {
//
//        RecordingDto saved = service.create(dto);
//
//        URI location = uriBuilder
//                .path("/api/recordings/{id}")
//                .buildAndExpand(saved.id())
//                .toUri();
//
//        return ResponseEntity.created(location).body(saved);
//    }

    @GetMapping("/{id}/karaoke")
    public RecordingKaraokeDto getKaraoke(@PathVariable String id) {
        return service.getKaraoke(id);
    }
}