package com.poetrystream.backend.dto;

public record ActorDto(
        String id,
        String name,
        String bio,
        String photoUrl
) {}