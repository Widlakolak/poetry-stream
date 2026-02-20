package com.poetrystream.backend.repository;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.domain.RecordingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecordingRepository extends JpaRepository<Recording, String> {

    List<Recording> findByTitleContainingIgnoreCase(String title);
    List<Recording> findByAuthorContainingIgnoreCase(String author);
    List<Recording> findByActorContainingIgnoreCase(String actor);
    List<Recording> findByStatus(RecordingStatus status);
}