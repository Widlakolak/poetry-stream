package com.poetrystream.backend.mapper;

import com.poetrystream.backend.domain.Poet;
import com.poetrystream.backend.dto.PoetDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PoetMapper {

    PoetDto toDto(Poet poet);

    @Mapping(target = "poems", ignore = true)
    Poet fromDto(PoetDto dto);
}