package com.poetrystream.backend.repository;

import com.poetrystream.backend.domain.Poem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoemRepository extends JpaRepository<Poem, String> {
}