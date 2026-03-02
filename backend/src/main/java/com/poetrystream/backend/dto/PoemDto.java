package com.poetrystream.backend.dto;

public record PoemDto(
        String id,
        String title,
        String text,
        String poetId,
        String poetName
) {}