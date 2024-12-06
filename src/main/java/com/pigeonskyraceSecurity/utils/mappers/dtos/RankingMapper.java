package com.pigeonskyraceSecurity.utils.mappers.dtos;

import com.pigeonskyraceSecurity.dtos.RankingDTO;
import com.pigeonskyraceSecurity.models.Ranking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RankingMapper {
    RankingMapper INSTANCE = Mappers.getMapper(RankingMapper.class);

    RankingDTO toDTO(Ranking ranking);

    @Mappings({
            @Mapping(target = "id", ignore = true), // Let the ID be auto-generated or set by the repository
//            @Mapping(target = "pigeon", source = "pigeon"), // Map Pigeon directly
//            @Mapping(target = "race", source = "race")    // Map Race directly
    })
    Ranking toRanking(RankingDTO rankingDTO);
}
