package com.poetrystream.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordingDto {

    private String id;

    @NotBlank(message = "Tytuł jest wymagany")
    private String title;

    @NotBlank(message = "Autor jest wymagany")
    private String author;
    private String actor;

    @NotBlank(message = "URL nagrania jest wymagany")
    private String audioUrl;
    private Integer startTimeSec;
}