package com.pigeonskyraceSecurity.repositories;

import com.pigeonskyraceSecurity.models.Breeder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BreederRepository extends JpaRepository<Breeder, UUID> {
    Breeder findByUsername(String username);
}
