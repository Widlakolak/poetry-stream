package com.poetrystream.backend.mapper;

import com.poetrystream.backend.domain.Actor;
import com.poetrystream.backend.dto.ActorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ActorMapper {

    ActorDto toDto(Actor actor);

    @Mapping(target = "recordings", ignore = true)
    Actor fromDto(ActorDto dto);
}