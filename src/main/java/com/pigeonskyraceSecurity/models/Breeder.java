package com.pigeonskyraceSecurity.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "breeders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Breeder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String username;
    private String password;
    private String loftName;
    private double longitude;
    private double latitude;
    private double finalScore;

    @OneToMany(mappedBy = "breeder", cascade = CascadeType.ALL)
    private List<Pigeon> pigeons;

}
