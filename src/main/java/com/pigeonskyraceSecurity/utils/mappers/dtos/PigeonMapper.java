package com.pigeonskyraceSecurity.utils.mappers.dtos;


import com.pigeonskyraceSecurity.dtos.PigeonDTO;
import com.pigeonskyraceSecurity.models.Pigeon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PigeonMapper {
    PigeonMapper INSTANCE = Mappers.getMapper(PigeonMapper.class);

    // ResponseDTO
    PigeonDTO toDTO(Pigeon pigeon);

    // RequestDTO
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "bandNumber", ignore = true),
    })
    Pigeon toPigeon(PigeonDTO pigeonDTO);
}
