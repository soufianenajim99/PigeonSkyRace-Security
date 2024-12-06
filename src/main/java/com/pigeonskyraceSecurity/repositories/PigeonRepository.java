package com.pigeonskyraceSecurity.repositories;

import com.pigeonskyraceSecurity.models.Pigeon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PigeonRepository extends JpaRepository<Pigeon, String> {
}
