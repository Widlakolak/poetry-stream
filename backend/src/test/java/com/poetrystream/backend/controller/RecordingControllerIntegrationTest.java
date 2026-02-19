package com.poetrystream.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.repository.RecordingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(properties = "spring.profiles.active=test")
@Transactional
class RecordingControllerIntegrationTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private RecordingRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        repository.deleteAll();
    }

    @Test
    void shouldCreateRecordingAndReturn201AndPersist() throws Exception {
        Recording input = Recording.builder()
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

        List<Recording> all = repository.findAll();
        assert all.size() == 1;
    }

    @Test
    void shouldReturnRecordingById() throws Exception {
        Recording saved = repository.save(
                Recording.builder()
                        .title("Test ID")
                        .author("Autor ID")
                        .audioUrl("https://example.com/id.mp3")
                        .startTimeSec(5)
                        .build()
        );

        mockMvc.perform(get("/api/recordings/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(saved.getId())))
                .andExpect(jsonPath("$.title", is("Test ID")))
                .andExpect(jsonPath("$.author", is("Autor ID")));
    }

    @Test
    void shouldReturnAllRecordings() throws Exception {
        repository.saveAll(List.of(
                Recording.builder()
                        .title("Rec1")
                        .author("A1")
                        .audioUrl("https://example.com/1.mp3")
                        .startTimeSec(0)
                        .build(),
                Recording.builder()
                        .title("Rec2")
                        .author("A2")
                        .audioUrl("https://example.com/2.mp3")
                        .startTimeSec(10)
                        .build()
        ));

        mockMvc.perform(get("/api/recordings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Rec1")))
                .andExpect(jsonPath("$[1].title", is("Rec2")));
    }

    @Test
    void shouldReturn400WhenTitleIsBlank() throws Exception {
        Recording invalid = Recording.builder()
                .title("") // pusty tytuł
                .author("Autor")
                .audioUrl("https://example.com/test.mp3")
                .build();

        mockMvc.perform(post("/api/recordings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }
}