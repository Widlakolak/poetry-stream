package com.poetrystream.backend.repository;

import com.poetrystream.backend.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class RecordingRepositoryTest {

    @Autowired
    private RecordingRepository recordingRepository;

    @Autowired
    private PoetRepository poetRepository;

    @Autowired
    private PoemRepository poemRepository;

    @Autowired
    private ActorRepository actorRepository;

    private Recording saveRecording(
            String title,
            RecordingStatus status,
            String poetName,
            String actorName
    ) {

        Poet poet = poetRepository.save(
                Poet.builder()
                        .name(poetName)
                        .build()
        );

        Poem poem = poemRepository.save(
                Poem.builder()
                        .title("Poem Title")
                        .text("Text")
                        .poet(poet)
                        .build()
        );

        Actor actor = actorRepository.save(
                Actor.builder()
                        .name(actorName)
                        .build()
        );

        return recordingRepository.save(
                Recording.builder()
                        .title(title)
                        .audioUrl("https://example.com/audio.mp3")
                        .startTimeSec(0)
                        .status(status)
                        .poem(poem)
                        .actor(actor)
                        .build()
        );
    }

    @BeforeEach
    void setUp() {
        recordingRepository.deleteAll();
    }

    @Test
    @DisplayName("Should find by status")
    void shouldFindByStatus() {

        saveRecording("Title1", RecordingStatus.PUBLISHED, "Mickiewicz", "Actor1");
        saveRecording("Title2", RecordingStatus.DRAFT, "Słowacki", "Actor2");

        Page<Recording> page = recordingRepository.findByStatus(
                RecordingStatus.PUBLISHED,
                PageRequest.of(0, 10)
        );

        assertThat(page.getContent()).hasSize(1);
        assertThat(page.getContent().get(0).getStatus())
                .isEqualTo(RecordingStatus.PUBLISHED);
    }

    @Test
    @DisplayName("Should find by title ignoring case")
    void shouldFindByTitleContainingIgnoreCase() {

        saveRecording("Pan Tadeusz", RecordingStatus.PUBLISHED, "Mickiewicz", "Actor1");

        List<Recording> result =
                recordingRepository.findByTitleContainingIgnoreCase("tadeusz");

        assertThat(result).hasSize(1);
    }

    @Test
    @DisplayName("Should find by actor name ignoring case")
    void shouldFindByActorNameContainingIgnoreCase() {

        saveRecording("Title", RecordingStatus.PUBLISHED, "Mickiewicz", "Jan Nowak");

        List<Recording> result =
                recordingRepository.findByActor_NameContainingIgnoreCase("jan");

        assertThat(result).hasSize(1);
    }

    @Test
    @DisplayName("Should find by poet name ignoring case")
    void shouldFindByPoetNameContainingIgnoreCase() {

        saveRecording("Title", RecordingStatus.PUBLISHED, "Adam Mickiewicz", "Actor");

        List<Recording> result =
                recordingRepository.findByPoem_Poet_NameContainingIgnoreCase("mickiewicz");

        assertThat(result).hasSize(1);
    }
}