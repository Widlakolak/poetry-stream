package com.poetrystream.backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "recordings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recording {

    @Id
    @Column(length = 36)
    private String id;

    @NotBlank(message = "Tytuł jest wymagany")
    @Size(max = 255, message = "Tytuł może mieć maksymalnie 255 znaków")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Autor jest wymagany")
    @Size(max = 255, message = "Autor może mieć maksymalnie 255 znaków")
    @Column(nullable = false)
    private String author;

    @Size(max = 255, message = "Aktor może mieć maksymalnie 255 znaków")
    private String actor;

    @NotBlank(message = "URL nagrania jest wymagany")
    @Pattern(regexp = "^https?://.*\\.(mp3|wav|ogg|m4a)$",
            message = "URL musi wskazywać na plik audio (mp3, wav, ogg, m4a)")
    @Column(nullable = false)
    private String audioUrl;

    @Min(value = 0, message = "Czas startu nie może być ujemny")
    private Integer startTimeSec;

    @ElementCollection
    @CollectionTable(name = "recording_lines", joinColumns = @JoinColumn(name = "recording_id"))
    @Column(name = "line_text")
    private List<String> lines;

    @PrePersist
    private void ensureId() {
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
    }
}