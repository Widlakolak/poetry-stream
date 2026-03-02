package com.poetrystream.backend.service;

import com.poetrystream.backend.domain.Actor;
import com.poetrystream.backend.dto.ActorDto;
import com.poetrystream.backend.exception.ResourceNotFoundException;
import com.poetrystream.backend.mapper.ActorMapper;
import com.poetrystream.backend.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper mapper;

    public List<ActorDto> getAll() {
        return actorRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public ActorDto getById(String id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actor not found"));
        return mapper.toDto(actor);
    }

    public ActorDto create(ActorDto dto) {
        Actor actor = mapper.fromDto(dto);
        Actor saved = actorRepository.save(actor);
        return mapper.toDto(saved);
    }
}