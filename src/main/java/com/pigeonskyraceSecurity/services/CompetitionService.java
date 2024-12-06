package com.pigeonskyraceSecurity.services;

import com.pigeonskyraceSecurity.dtos.CompetitionDTO;

import java.util.List;

public interface CompetitionService {
    CompetitionDTO save(CompetitionDTO competitionDTO);
    CompetitionDTO update(String id, CompetitionDTO competitionDTO);
    List<CompetitionDTO> saveAll(List<CompetitionDTO> competitionDTOs);
    List<CompetitionDTO> findAll();
    CompetitionDTO findById(String id);
}
