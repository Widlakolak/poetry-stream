package com.poetrystream.backend.repository;

import com.poetrystream.backend.domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, String> {
}