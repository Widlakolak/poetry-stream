package com.poetrystream.backend.service;

import com.poetrystream.backend.domain.Poet;
import com.poetrystream.backend.dto.PoetDto;
import com.poetrystream.backend.exception.ResourceNotFoundException;
import com.poetrystream.backend.mapper.PoetMapper;
import com.poetrystream.backend.repository.PoetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoetService {

    private final PoetRepository poetRepository;
    private final PoetMapper mapper;

    public List<PoetDto> getAll() {
        return poetRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public PoetDto getById(String id) {
        Poet poet = poetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poet not found"));
        return mapper.toDto(poet);
    }

    public PoetDto create(PoetDto dto) {
        Poet poet = mapper.fromDto(dto);
        Poet saved = poetRepository.save(poet);
        return mapper.toDto(saved);
    }
}