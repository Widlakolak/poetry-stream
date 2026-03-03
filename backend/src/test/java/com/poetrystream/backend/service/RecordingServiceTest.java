package com.poetrystream.backend.service;

import com.poetrystream.backend.domain.*;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.dto.RecordingKaraokeDto;
import com.poetrystream.backend.exception.ResourceNotFoundException;
import com.poetrystream.backend.mapper.RecordingMapper;
import com.poetrystream.backend.repository.ActorRepository;
import com.poetrystream.backend.repository.PoemRepository;
import com.poetrystream.backend.repository.RecordingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class RecordingServiceTest {

    @Mock
    private RecordingRepository recordingRepository;

    @Mock
    private PoemRepository poemRepository;

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private RecordingMapper mapper;

    @InjectMocks
    private RecordingService service;

    @Test
    void shouldUsePublishedStatusWhenStatusIsNull() {

        Pageable pageable = PageRequest.of(0, 10);
        Recording recording = Recording.builder().build();
        Page<Recording> page = new PageImpl<>(List.of(recording));

        when(recordingRepository.findByStatus(RecordingStatus.PUBLISHED, pageable))
                .thenReturn(page);

        when(mapper.toDto(recording))
                .thenReturn(mock(RecordingDto.class));

        Page<RecordingDto> result = service.getAll(null, pageable);

        assertThat(result.getContent()).hasSize(1);
        verify(recordingRepository)
                .findByStatus(RecordingStatus.PUBLISHED, pageable);
    }

    @Test
    void shouldUseProvidedStatus() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Recording> page = new PageImpl<>(List.of());

        when(recordingRepository.findByStatus(RecordingStatus.DRAFT, pageable))
                .thenReturn(page);

        service.getAll(RecordingStatus.DRAFT, pageable);

        verify(recordingRepository)
                .findByStatus(RecordingStatus.DRAFT, pageable);
    }

    @Test
    void shouldReturnRecordingById() {

        Recording recording = Recording.builder().id("1").build();
        RecordingDto dto = mock(RecordingDto.class);

        when(recordingRepository.findById("1"))
                .thenReturn(Optional.of(recording));

        when(mapper.toDto(recording))
                .thenReturn(dto);

        RecordingDto result = service.getById("1");

        assertThat(result).isEqualTo(dto);
    }

    @Test
    void shouldThrowWhenRecordingNotFound() {

        when(recordingRepository.findById("1"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getById("1"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Recording not found");
    }

    @Test
    void shouldCreateRecordingWithDraftStatus() {

        Poem poem = Poem.builder().id("poem1").build();
        Actor actor = Actor.builder().id("actor1").build();

        RecordingDto inputDto = new RecordingDto(
                null,
                "Title",
                "audio.mp3",
                10,
                RecordingStatus.PUBLISHED,
                "poem1",
                null,
                null,
                "actor1",
                null
        );

        when(poemRepository.findById("poem1"))
                .thenReturn(Optional.of(poem));

        when(actorRepository.findById("actor1"))
                .thenReturn(Optional.of(actor));

        when(recordingRepository.save(any(Recording.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(mapper.toDto(any(Recording.class)))
                .thenReturn(mock(RecordingDto.class));

        service.create(inputDto);

        ArgumentCaptor<Recording> captor =
                ArgumentCaptor.forClass(Recording.class);

        verify(recordingRepository).save(captor.capture());

        Recording saved = captor.getValue();

        assertThat(saved.getStatus()).isEqualTo(RecordingStatus.DRAFT);
        assertThat(saved.getPoem()).isEqualTo(poem);
        assertThat(saved.getActor()).isEqualTo(actor);
    }

    @Test
    void shouldThrowWhenPoemNotFound() {

        RecordingDto dto = mock(RecordingDto.class);
        when(dto.poemId()).thenReturn("poem1");

        when(poemRepository.findById("poem1"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.create(dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Poem not found");
    }

    @Test
    void shouldThrowWhenActorNotFound() {

        Poem poem = Poem.builder().id("poem1").build();

        RecordingDto dto = mock(RecordingDto.class);
        when(dto.poemId()).thenReturn("poem1");
        when(dto.actorId()).thenReturn("actor1");

        when(poemRepository.findById("poem1"))
                .thenReturn(Optional.of(poem));

        when(actorRepository.findById("actor1"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.create(dto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Actor not found");
    }

    @Test
    void shouldReturnKaraokeDto() {

        Recording recording = Recording.builder().id("1").build();
        RecordingKaraokeDto karaokeDto = mock(RecordingKaraokeDto.class);

        when(recordingRepository.findById("1"))
                .thenReturn(Optional.of(recording));

        when(mapper.toKaraokeDto(recording))
                .thenReturn(karaokeDto);

        RecordingKaraokeDto result = service.getKaraoke("1");

        assertThat(result).isEqualTo(karaokeDto);
    }

    @Test
    void shouldThrowWhenRecordingNotFoundForKaraoke() {

        when(recordingRepository.findById("1"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getKaraoke("1"))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Recording not found");
    }
}