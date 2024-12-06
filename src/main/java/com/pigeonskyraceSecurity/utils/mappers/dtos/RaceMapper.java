package com.pigeonskyraceSecurity.utils.mappers.dtos;


import com.pigeonskyraceSecurity.dtos.RaceDTO;
import com.pigeonskyraceSecurity.models.Race;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RaceMapper {
    RaceMapper INSTANCE = Mappers.getMapper(RaceMapper.class);

    RaceDTO toDTO(Race race);

    @Mappings({
            @Mapping(target = "id", ignore = true),
    })
    Race toRace(RaceDTO raceDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRaceFromDto(RaceDTO dto, @MappingTarget Race entity);
}
