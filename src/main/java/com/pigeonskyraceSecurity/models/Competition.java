package com.pigeonskyraceSecurity.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "competitions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Competition {
    @Id
    private String id;
    private String name;
    private LocalDateTime closedAt;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Race> races;

}
