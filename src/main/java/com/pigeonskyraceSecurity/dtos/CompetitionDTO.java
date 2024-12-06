package com.pigeonskyraceSecurity.dtos;
import lombok.Builder;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;



import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CompetitionDTO(
        String id,
        @NotBlank String name,
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime closedAt,
        List<RaceDTO> races)
{

}
