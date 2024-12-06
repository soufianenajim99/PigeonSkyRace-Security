package com.pigeonskyraceSecurity.repositories;

import com.pigeonskyraceSecurity.models.Breeder;
import com.pigeonskyraceSecurity.models.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RaceRepository extends JpaRepository<Race, UUID> {

    @Query(value = """
        SELECT DISTINCT b.*
        FROM breeders b
        JOIN pigeons p ON p.breeder_id = b.id
        JOIN rankings r ON r.pigeon_id = p.id
        JOIN races race ON race.id = r.race_id
        WHERE race.id = :raceId
    """, nativeQuery = true)
    List<Breeder> findDistinctLoftsByRaceId(@Param("raceId") UUID raceId);
}
