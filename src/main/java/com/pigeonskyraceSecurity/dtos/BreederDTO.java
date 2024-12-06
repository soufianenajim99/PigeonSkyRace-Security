package com.pigeonskyraceSecurity.dtos;

import lombok.Builder;

import java.util.List;

@Builder
public record BreederDTO(String id,
                         String username,
                         String password,
                         String loftName,
                         double longitude,
                         double latitude,
                         double finalScore,
                         List<PigeonDTO> pigeons) {
}
