package com.pigeonskyraceSecurity.repositories;

import com.pigeonskyraceSecurity.models.Breeder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreederRepository extends JpaRepository<Breeder, String> {
    Breeder findByUsername(String username);
}
