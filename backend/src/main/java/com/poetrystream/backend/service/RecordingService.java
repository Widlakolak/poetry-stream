package com.poetrystream.backend.service;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.domain.RecordingStatus;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.dto.RecordingKaraokeDto;
import com.poetrystream.backend.exception.ResourceNotFoundException;
import com.poetrystream.backend.mapper.RecordingMapper;
import com.poetrystream.backend.repository.RecordingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordingService {

    private final RecordingRepository repository;
    private final RecordingMapper mapper;

    public RecordingDto getRecordingById(String id) {
        Recording recording = repository.findById(id)
                .filter(r -> r.getStatus() == RecordingStatus.PUBLISHED)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recording not found with id: " + id));

        return mapper.toDto(recording);
    }

    public List<RecordingDto> getAllRecordings() {
        return repository.findByStatus(RecordingStatus.PUBLISHED)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public Recording save(Recording recording) {
        return repository.save(recording);
    }

    public RecordingKaraokeDto getKaraoke(String id) {
        Recording recording = repository.findById(id)
                .filter(r -> r.getStatus() == RecordingStatus.PUBLISHED)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recording not found with id: " + id));

        return mapper.toKaraokeDto(recording);
    }
}