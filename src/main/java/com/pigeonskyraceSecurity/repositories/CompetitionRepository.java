package com.pigeonskyraceSecurity.repositories;

import com.pigeonskyraceSecurity.models.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, String> {
}
