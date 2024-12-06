package com.pigeonskyraceSecurity.models;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.pigeonskyraceSecurity.utils.csvConverters.LocalTimeConverter;
import com.pigeonskyraceSecurity.utils.csvConverters.PigeonConverter;
import com.pigeonskyraceSecurity.utils.csvConverters.RaceConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalTime;

@Entity
@Table(name = "rankings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ranking {
    @Id
    @CsvBindByName
    private String id;

    @CsvCustomBindByName(converter = LocalTimeConverter.class)
    private LocalTime startTime;
    @CsvBindByName
    private double distance;
    @CsvBindByName
    private double adjustedSpeed;
    @CsvBindByName
    private double score;


    @CsvCustomBindByName(converter = PigeonConverter.class)
    @ManyToOne
    @JoinColumn(name = "pigeon_id", nullable = false)
    private Pigeon pigeon;

    @ManyToOne
    @JoinColumn(name = "race_id", nullable = false)
    @CsvCustomBindByName(converter = RaceConverter.class)
    private Race race;
}
