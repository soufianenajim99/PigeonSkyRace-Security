package com.pigeonskyraceSecurity.dtos;

import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record BreederDTO(UUID id,
                         String username,
                         String password,
                         String loftName,
                         double longitude,
                         double latitude,
                         double finalScore,
                         List<PigeonDTO> pigeons) {
}
