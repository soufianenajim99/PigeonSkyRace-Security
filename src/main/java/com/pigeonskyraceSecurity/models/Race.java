package com.pigeonskyraceSecurity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "races")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Race {
    @Id
    private String id;
    private String name;
    private double longitude;
    private double latitude;
    private LocalDateTime startDate;
    private double targetDistance;
    private double tolerance;
    private double avgDistance;
    private LocalDateTime closedAt;
    private boolean autoAdj;

    @ManyToOne
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;



    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private List<Ranking> rankings;
}
