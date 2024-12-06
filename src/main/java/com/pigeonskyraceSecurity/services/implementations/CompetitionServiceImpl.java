package com.pigeonskyraceSecurity.services.implementations;


import com.pigeonskyraceSecurity.dtos.CompetitionDTO;
import com.pigeonskyraceSecurity.exception.ResourceNotFoundException;
import com.pigeonskyraceSecurity.models.Competition;
import com.pigeonskyraceSecurity.repositories.CompetitionRepository;
import com.pigeonskyraceSecurity.services.CompetitionService;
import com.pigeonskyraceSecurity.utils.mappers.dtos.CompetitionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final CompetitionMapper competitionMapper;

    @Autowired
    public CompetitionServiceImpl(CompetitionRepository competitionRepository, CompetitionMapper competitionMapper) {
        this.competitionRepository = competitionRepository;
        this.competitionMapper = competitionMapper;
    }

    @Override
    public CompetitionDTO save(CompetitionDTO competitionDTO) {
        Competition competition = competitionMapper.toCompetition(competitionDTO);
        competition = competitionRepository.save(competition);
        return competitionMapper.toDTO(competition);
    }

    @Override
    public CompetitionDTO update(String id, CompetitionDTO competitionDTO) {
        Competition competition = findEntityById(id);
        competitionMapper.updateCompetitionFromDto(competitionDTO, competition);
        competition = competitionRepository.save(competition);
        return competitionMapper.toDTO(competition);
    }

    public Competition findEntityById(String id) {
        Optional<Competition> competition = competitionRepository.findById(id);
        if(competition.isPresent()){
            return competition.get();
        } else {
            throw new ResourceNotFoundException("Competition not found");
        }
    }

    @Override
    public CompetitionDTO findById(String id) {
        Optional<Competition> competition = competitionRepository.findById(id);
        if(competition.isPresent()){
            return competition.map(competitionMapper::toDTO).orElse(null);
        } else {
            throw new ResourceNotFoundException("Competition not found");
        }
    }

    @Override
    public List<CompetitionDTO> saveAll(List<CompetitionDTO> competitionDTOs) {
        return competitionDTOs.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public List<CompetitionDTO> findAll() {
        return competitionRepository.findAll().stream()
                .map(competitionMapper::toDTO)
                .toList();
    }
}
