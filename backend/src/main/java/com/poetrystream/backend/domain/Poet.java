package com.poetrystream.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "poets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Poet {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(length = 5000)
    private String bio;

    private Integer birthYear;
    private Integer deathYear;

    private String photoUrl;

    @OneToMany(mappedBy = "poet")
    private List<Poem> poems;

    @PrePersist
    private void ensureId() {
        if (id == null || id.isBlank()) {
            id = UUID.randomUUID().toString();
        }
    }
}