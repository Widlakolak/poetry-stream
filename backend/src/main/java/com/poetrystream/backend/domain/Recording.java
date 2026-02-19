package com.poetrystream.backend.domain;

import jakarta.persistence.*;
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

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private String actor;

    @Column(nullable = false)
    private String audioUrl;

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