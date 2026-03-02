package com.poetrystream.backend.dto;

public record PoetDto(
        String id,
        String name,
        String bio,
        Integer birthYear,
        Integer deathYear,
        String photoUrl
) {}