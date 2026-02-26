package com.poetrystream.backend.mapper;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.dto.RecordingKaraokeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import javax.sound.sampled.Line;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecordingMapper {

    RecordingDto toDto(Recording recording);

    default RecordingKaraokeDto toKaraokeDto(Recording recording) {
        List<String> lines = recording.getLines()
                .stream()
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