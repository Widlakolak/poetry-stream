package com.poetrystream.backend.service;

import com.poetrystream.backend.domain.Actor;
import com.poetrystream.backend.domain.Poem;
import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.domain.RecordingStatus;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.dto.RecordingKaraokeDto;
import com.poetrystream.backend.exception.ResourceNotFoundException;
import com.poetrystream.backend.mapper.RecordingMapper;
import com.poetrystream.backend.repository.ActorRepository;
import com.poetrystream.backend.repository.PoemRepository;
import com.poetrystream.backend.repository.RecordingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordingService {

    private final RecordingRepository recordingRepository;
    private final PoemRepository poemRepository;
    private final ActorRepository actorRepository;
    private final RecordingMapper mapper;

    public Page<RecordingDto> getAll(RecordingStatus status, Pageable pageable) {
        if (status == null) {
            status = RecordingStatus.PUBLISHED;
        }
        return recordingRepository
                .findByStatus(status, pageable)
                .map(mapper::toDto);
    }

    public RecordingDto getById(String id) {
        Recording recording = recordingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recording not found"));

        return mapper.toDto(recording);
    }

    public RecordingDto create(RecordingDto dto) {

        Poem poem = poemRepository.findById(dto.poemId())
                .orElseThrow(() -> new ResourceNotFoundException("Poem not found"));

        Actor actor = actorRepository.findById(dto.actorId())
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found"));

        Recording recording = Recording.builder()
                .title(dto.title())
                .audioUrl(dto.audioUrl())
                .startTimeSec(dto.startTimeSec())
                .status(RecordingStatus.DRAFT)
                .poem(poem)
                .actor(actor)
                .build();

        Recording saved = recordingRepository.save(recording);

        return mapper.toDto(saved);
    }

    public RecordingKaraokeDto getKaraoke(String id) {
        Recording recording = recordingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recording not found"));

        return mapper.toKaraokeDto(recording);
    }
}