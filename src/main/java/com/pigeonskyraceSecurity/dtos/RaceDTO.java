package com.pigeonskyraceSecurity.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record RaceDTO(
        UUID id,
        @NotBlank String name,
        @NotNull double longitude,
        @NotNull double latitude,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime startDate,
        @NotNull double targetDistance,
        double tolerance,
        double avgDistance,
        boolean autoAdj,
        @NotNull LocalDateTime closedAt,
        List<RankingDTO> rankings ,
        CompetitionDTO competition){

    public RaceDTO withCompetitionId(UUID competitionId) {
        return new RaceDTO(id, name, longitude,latitude, startDate, targetDistance,tolerance,avgDistance,autoAdj, closedAt,rankings,
                CompetitionDTO.builder().id(competitionId).build());
    }



}
