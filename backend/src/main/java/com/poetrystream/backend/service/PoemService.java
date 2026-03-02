package com.poetrystream.backend.service;

import com.poetrystream.backend.domain.Poem;
import com.poetrystream.backend.domain.Poet;
import com.poetrystream.backend.dto.PoemDto;
import com.poetrystream.backend.exception.ResourceNotFoundException;
import com.poetrystream.backend.mapper.PoemMapper;
import com.poetrystream.backend.repository.PoemRepository;
import com.poetrystream.backend.repository.PoetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PoemService {

    private final PoemRepository poemRepository;
    private final PoetRepository poetRepository;
    private final PoemMapper mapper;

    public List<PoemDto> getAll() {
        return poemRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public PoemDto getById(String id) {
        Poem poem = poemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Poem not found"));
        return mapper.toDto(poem);
    }

    public PoemDto create(PoemDto dto) {
        Poet poet = poetRepository.findById(dto.poetId())
                .orElseThrow(() -> new ResourceNotFoundException("Poet not found"));

        Poem poem = mapper.fromDto(dto);
        poem.setPoet(poet);

        Poem saved = poemRepository.save(poem);
        return mapper.toDto(saved);
    }
}