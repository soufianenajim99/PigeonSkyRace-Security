package com.pigeonskyraceSecurity.dtos;


import com.pigeonskyraceSecurity.utils.enums.Gender;
import jakarta.validation.constraints.NotBlank;

import lombok.Builder;

@Builder
public record PigeonDTO(String id,
                        String bandNumber,
                        Gender gender,
                        @NotBlank String birthYear,
                        String color,
                        String image,
                        BreederDTO breeder) {

    public PigeonDTO withBreederId(String breederId) {
        return new PigeonDTO(id, bandNumber, gender, birthYear, color, image, BreederDTO.builder().id(breederId).build());
    }


}
