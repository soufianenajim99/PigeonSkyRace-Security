package com.pigeonskyraceSecurity.models;

import com.pigeonskyraceSecurity.utils.enums.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pigeons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Pigeon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String bandNumber;

    @NotEmpty
    private Gender gender;

    @NotBlank
    private String birthYear;
    private String color;
    private String image;

    @ManyToOne
    @JoinColumn(name = "breeder_id", nullable = false)
    private Breeder breeder;

    @OneToMany(mappedBy = "pigeon", cascade = CascadeType.ALL)
    private List<Ranking> rankings;
}
