package com.poetrystream.backend.repository;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.domain.RecordingStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RecordingRepository extends JpaRepository<Recording, String> {

    List<Recording> findByTitleContainingIgnoreCase(String title);
    List<Recording> findByPoem_Poet_NameContainingIgnoreCase(String poetName);
    List<Recording> findByActor_NameContainingIgnoreCase(String actorName);
    Page<Recording> findByStatus(RecordingStatus status, Pageable pageable);
}