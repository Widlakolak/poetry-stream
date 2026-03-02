package com.poetrystream.backend.mapper;

import com.poetrystream.backend.domain.Poem;
import com.poetrystream.backend.dto.PoemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PoemMapper {

    @Mapping(source = "poet.id", target = "poetId")
    @Mapping(source = "poet.name", target = "poetName")
    PoemDto toDto(Poem poem);

    @Mapping(target = "recordings", ignore = true)
    @Mapping(target = "poet", ignore = true)
    Poem fromDto(PoemDto dto);
}