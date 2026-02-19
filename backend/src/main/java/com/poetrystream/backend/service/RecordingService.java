package com.poetrystream.backend.service;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.exception.ResourceNotFoundException;
import com.poetrystream.backend.repository.RecordingRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordingService {

    private final RecordingRepository repository;

    public RecordingDto getRecordingById(String id) {
        Recording recording = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recording not found with id: " + id));
        return mapToDto(recording);
    }

    public List<RecordingDto> getAllRecordings() {
        return repository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public Recording save(Recording recording) {
        return repository.save(recording);
    }

    public RecordingDto mapToDto(Recording recording) {
        return RecordingDto.builder()
                .id(recording.getId())
                .title(recording.getTitle())
                .author(recording.getAuthor())
                .actor(recording.getActor())
                .audioUrl(recording.getAudioUrl())
                .startTimeSec(recording.getStartTimeSec())
                .build();
    }
}
