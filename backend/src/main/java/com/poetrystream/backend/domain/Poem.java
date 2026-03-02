package com.poetrystream.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "poems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poem {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String text;

    @ManyToOne
    @JoinColumn(name = "poet_id", nullable = false)
    private Poet poet;

    @OneToMany(mappedBy = "poem")
    private List<Recording> recordings;

    @PrePersist
    private void ensureId() {
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
    }
}