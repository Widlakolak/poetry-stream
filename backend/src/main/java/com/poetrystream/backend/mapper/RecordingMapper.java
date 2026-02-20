package com.poetrystream.backend.mapper;

import com.poetrystream.backend.domain.Recording;
import com.poetrystream.backend.dto.RecordingDto;
import com.poetrystream.backend.dto.RecordingKaraokeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecordingMapper {

    RecordingDto toDto(Recording recording);

    RecordingKaraokeDto toKaraokeDto(Recording recording);
}