package com.poetrystream.backend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecordingDto {

    private String id;
    private String title;
    private String author;
    private String actor;
    private String audioUrl;
    private Integer startTimeSec;
}