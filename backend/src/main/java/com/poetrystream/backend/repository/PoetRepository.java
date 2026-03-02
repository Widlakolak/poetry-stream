package com.poetrystream.backend.repository;

import com.poetrystream.backend.domain.Poet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoetRepository extends JpaRepository<Poet, String> {
}