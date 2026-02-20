package com.poetrystream.backend.dto;

import java.util.List;

public record RecordingKaraokeDto(
        String id,
        String audioUrl,
        Integer startTimeSec,
        List<String> lines
) {}

