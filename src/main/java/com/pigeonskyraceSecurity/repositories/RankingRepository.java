package com.pigeonskyraceSecurity.repositories;

import com.pigeonskyraceSecurity.models.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, UUID> {
}
