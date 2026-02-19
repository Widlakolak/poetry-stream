package com.poetrystream.backend.controller;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.service.RecordingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recordings")
@RequiredArgsConstructor
public class RecordingController {

    private final RecordingService service;

    @GetMapping
    public List<Recording> getAll() {
        return service.getAllRecordings();
    }

    @PostMapping
    public Recording add(@RequestBody Recording recording) {
        return service.save(recording);
    }
}