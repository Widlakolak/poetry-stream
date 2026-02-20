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
    @Size(max = 255)
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Autor jest wymagany")
    @Size(max = 255)
    @Column(nullable = false)
    private String author;

    @Size(max = 255)
    private String actor;

    @NotBlank(message = "URL nagrania jest wymagany")
    @Pattern(regexp = "^https?://.*\\.(mp3|wav|ogg|m4a)$")
    @Column(name = "audio_url", nullable = false, length = 512)
    private String audioUrl;

    @Min(0)
    @Column(name = "start_time_sec")
    private Integer startTimeSec;

    @ElementCollection
    @CollectionTable(name = "recording_lines",
            joinColumns = @JoinColumn(name = "recording_id"))
    @Column(name = "line_text")
    @OrderColumn(name = "line_order")
    private List<String> lines;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecordingStatus status;

    @PrePersist
    private void ensureId() {
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }

        if (status == null) {
            status = RecordingStatus.DRAFT;
        }
    }
}