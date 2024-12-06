package com.pigeonskyraceSecurity.services;

import com.pigeonskyraceSecurity.dtos.RaceDTO;
import com.pigeonskyraceSecurity.models.Breeder;

import java.util.List;


public interface RaceService {
    RaceDTO save(RaceDTO raceDTO);
    RaceDTO update(String id, RaceDTO raceDTO);
    List<RaceDTO> saveAll(List<RaceDTO> raceDTOs);
    List<RaceDTO> findAll();
    RaceDTO findById(String id);
    RaceDTO close(String id);
    double calculateAvgDistance(String id);
    List<Breeder> getParticipatingBreeders(String id);
}
