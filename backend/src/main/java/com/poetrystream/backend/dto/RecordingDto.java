package com.poetrystream.backend.dto;

import com.poetrystream.backend.domain.RecordingStatus;

public record RecordingDto(
        String id,
        String title,
        String audioUrl,
        Integer startTimeSec,
        RecordingStatus status,
        String poemId,
        String poemTitle,
        String poetName,
        String actorId,
        String actorName
) {}