package com.poetrystream.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poetrystream.backend.domain.*;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class RecordingControllerIntegrationTest {

    @Autowired MockMvc mockMvc;

    private final ObjectMapper objectMapper =
            new ObjectMapper().findAndRegisterModules();

    @Autowired RecordingRepository recordingRepository;
    @Autowired PoemRepository poemRepository;
    @Autowired ActorRepository actorRepository;
    @Autowired PoetRepository poetRepository;

    private Poem poem;
    private Actor actor;

    @BeforeEach
    void setup() {
        recordingRepository.deleteAll();
        poemRepository.deleteAll();
        actorRepository.deleteAll();
        poetRepository.deleteAll();

        Poet poet = poetRepository.save(
                Poet.builder()
                        .name("Test Poet")
                        .build()
        );

        poem = poemRepository.save(
                Poem.builder()
                        .title("Test Poem")
                        .text("Line 1\nLine 2")
                        .poet(poet)
                        .build()
        );

        actor = actorRepository.save(
                Actor.builder()
                        .name("Test Actor")
                        .build()
        );
    }

    @Test
    void shouldCreateRecording() throws Exception {

        RecordingDto dto = new RecordingDto(
                null,
                "Nowe nagranie",
                "https://example.com/test.mp3",
                0,
                null,
                poem.getId(),
                null,
                null,
                actor.getId(),
                null
        );

        mockMvc.perform(post("/api/recordings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Nowe nagranie"))
                .andExpect(jsonPath("$.status").value("DRAFT"))
                .andExpect(jsonPath("$.poemId").value(poem.getId()))
                .andExpect(jsonPath("$.actorId").value(actor.getId()));
    }

    @Test
    void shouldReturnPagedRecordings() throws Exception {

        recordingRepository.save(
                Recording.builder()
                        .title("Rec1")
                        .audioUrl("https://a.com/1.mp3")
                        .status(RecordingStatus.PUBLISHED)
                        .poem(poem)
                        .actor(actor)
                        .build()
        );

        mockMvc.perform(get("/api/recordings")
                        .param("status", "PUBLISHED"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(1))
                .andExpect(jsonPath("$.content[0].title").value("Rec1"));
    }

    @Test
    void shouldReturnRecordingById() throws Exception {

        Recording saved = recordingRepository.save(
                Recording.builder()
                        .title("Test ID")
                        .audioUrl("https://example.com/id.mp3")
                        .status(RecordingStatus.PUBLISHED)
                        .poem(poem)
                        .actor(actor)
                        .build()
        );

        mockMvc.perform(get("/api/recordings/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()));
    }

    @Test
    void shouldReturnKaraokeData() throws Exception {

        Recording saved = recordingRepository.save(
                Recording.builder()
                        .title("Wiersz")
                        .audioUrl("https://example.com/a.mp3")
                        .status(RecordingStatus.PUBLISHED)
                        .poem(poem)
                        .actor(actor)
                        .lines(List.of("Linia 1", "Linia 2"))
                        .build()
        );

        mockMvc.perform(get("/api/recordings/" + saved.getId() + "/karaoke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lines.length()").value(2));
    }

    @Test
    void shouldReturn404WhenRecordingNotFound() throws Exception {

        mockMvc.perform(get("/api/recordings/non-existing-id"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Recording not found"));
    }
}