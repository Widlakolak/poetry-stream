package com.poetrystream.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.domain.RecordingStatus;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.repository.RecordingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Import(JacksonAutoConfiguration.class)
@Transactional
class RecordingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules();

    @Autowired
    private RecordingRepository repository;

    @BeforeEach
    void setup() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateRecordingAndReturn201AndPersist() throws Exception {
        RecordingDto input = RecordingDto.builder()
                .title("Testowy wiersz")
                .author("Test Autor")
                .audioUrl("https://example.com/test.mp3")
                .startTimeSec(0)
                .build();

        mockMvc.perform(post("/api/recordings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.title", is("Testowy wiersz")))
                .andExpect(jsonPath("$.author", is("Test Autor")));
    }

    @Test
    void shouldReturnRecordingById() throws Exception {
        Recording saved = repository.save(
                Recording.builder()
                        .title("Test ID")
                        .author("Autor ID")
                        .audioUrl("https://example.com/id.mp3")
                        .startTimeSec(5)
                        .status(RecordingStatus.PUBLISHED)
                        .lines(List.of("Linia1", "Linia2"))
                        .build()
        );

        mockMvc.perform(get("/api/recordings/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(saved.getId())))
                .andExpect(jsonPath("$.title", is("Test ID")));
    }

    @Test
    void shouldReturnAllRecordings() throws Exception {
        repository.saveAll(List.of(
                Recording.builder()
                        .title("Rec1").author("A1").audioUrl("https://a.com/1.mp3")
                        .status(RecordingStatus.PUBLISHED)
                        .build(),
                Recording.builder()
                        .title("Rec2").author("A2").audioUrl("https://a.com/2.mp3")
                        .status(RecordingStatus.PUBLISHED)
                        .build()
        ));

        mockMvc.perform(get("/api/recordings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void shouldReturn400WhenTitleIsBlank() throws Exception {
        RecordingDto invalid = RecordingDto.builder()
                .title("")
                .author("Autor")
                .audioUrl("https://example.com/test.mp3")
                .build();

        mockMvc.perform(post("/api/recordings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnKaraokeDataWithLines() throws Exception {
        Recording saved = repository.save(Recording.builder()
                .title("Wiersz")
                .author("Autor")
                .audioUrl("https://example.com/a.mp3")
                .lines(List.of("Linia 1", "Linia 2"))
                .status(RecordingStatus.PUBLISHED)
                .build());

        mockMvc.perform(get("/api/recordings/" + saved.getId() + "/karaoke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lines", hasSize(2)))
                .andExpect(jsonPath("$.lines[0]", is("Linia 1")));
    }
}