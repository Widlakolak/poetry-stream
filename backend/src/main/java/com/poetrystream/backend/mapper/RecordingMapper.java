package com.poetrystream.backend.mapper;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.dto.RecordingKaraokeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;
import java.util.Objects;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface RecordingMapper {

    @Mapping(source = "poem.id", target = "poemId")
    @Mapping(source = "poem.title", target = "poemTitle")
    @Mapping(source = "poem.poet.name", target = "poetName")
    @Mapping(source = "actor.id", target = "actorId")
    @Mapping(source = "actor.name", target = "actorName")
    @Mapping(source = "status", target = "status")
    RecordingDto toDto(Recording recording);

    default RecordingKaraokeDto toKaraokeDto(Recording recording) {

        List<String> lines = recording.getLines() == null
                ? List.of()
                : recording.getLines().stream()
                .filter(Objects::nonNull)
                .toList();

        return new RecordingKaraokeDto(
                recording.getId(),
                recording.getAudioUrl(),
                recording.getStartTimeSec(),
                lines,
                lines.size()
        );
    }
}