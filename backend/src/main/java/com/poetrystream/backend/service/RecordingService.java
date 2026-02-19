package com.poetrystream.backend.service;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.repository.RecordingRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordingService {

    private final RecordingRepository repository;

    public List<Recording> getAllRecordings() {
        return repository.findAll();
    }

    public Recording save(Recording recording) {
        return repository.save(recording);
    }

    // w przyszłości:
    // public Recording getById(String id) { return repository.findById(id).orElseThrow(...); }
}
