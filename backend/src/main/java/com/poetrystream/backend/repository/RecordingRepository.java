package com.poetrystream.backend.repository;

import com.poetrystream.backend.domain.Recording;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordingRepository extends JpaRepository<Recording, String> {
    // metody np.:
    // List<Recording> findByAuthor(String author);
    // List<Recording> findByTitleContainingIgnoreCase(String titlePart);
}