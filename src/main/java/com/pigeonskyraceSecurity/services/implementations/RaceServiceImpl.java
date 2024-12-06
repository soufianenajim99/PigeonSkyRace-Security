package com.pigeonskyraceSecurity.services.implementations;


import com.pigeonskyraceSecurity.dtos.RaceDTO;
import com.pigeonskyraceSecurity.exception.ResourceNotFoundException;
import com.pigeonskyraceSecurity.models.Breeder;
import com.pigeonskyraceSecurity.models.Race;
import com.pigeonskyraceSecurity.repositories.RaceRepository;
import com.pigeonskyraceSecurity.services.RaceService;
import com.pigeonskyraceSecurity.utils.GeoUtil;
import com.pigeonskyraceSecurity.utils.mappers.dtos.BreederMapper;
import com.pigeonskyraceSecurity.utils.mappers.dtos.RaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RaceServiceImpl implements RaceService {

    private RaceRepository raceRepository;
    private RaceMapper raceMapper;
    private GeoUtil geoUtil;
    private BreederMapper breederMapper;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, RaceMapper raceMapper, GeoUtil geoUtil) {
        this.raceRepository = raceRepository;
        this.raceMapper = raceMapper;
        this.geoUtil = geoUtil;
    }


    @Override
    public RaceDTO save(RaceDTO raceDTO) {
        Race race = raceMapper.toRace(raceDTO);
        Race savedRace = raceRepository.save(race);
        return findById(String.valueOf(savedRace.getId()));
    }

    @Override
    public RaceDTO update(String id, RaceDTO raceDTO) {
        Race race = findEntityById(id);
        raceMapper.updateRaceFromDto(raceDTO, race);
        race = raceRepository.save(race);
        return raceMapper.toDTO(race);
    }

    @Override
    public List<RaceDTO> saveAll(List<RaceDTO> raceDTOs) {
        return raceDTOs.stream()
                .map(this::save)
                .toList();
    }

    @Override
    public List<RaceDTO> findAll() {
        return raceRepository.findAll().stream()
                .map(raceMapper::toDTO)
                .toList();
    }

    public Race findEntityById(String id) {
        Optional<Race> race = raceRepository.findById(UUID.fromString(id));
        if(race.isPresent()){
            return race.get();
        } else {
            throw new ResourceNotFoundException("Race not found");
        }
    }

    @Override
    public RaceDTO findById(String id) {
        return raceMapper.toDTO(findEntityById(id));
    }

    @Override
    public RaceDTO close(String id) {
        double avgDistance = calculateAvgDistance(id);
        RaceDTO newRaceDTO = RaceDTO.builder().closedAt(LocalDateTime.now()).avgDistance(avgDistance).build();
        return update(id, newRaceDTO);
    }

    @Override
    public double calculateAvgDistance(String id) {
        List<Breeder> breeders = getParticipatingBreeders(id);
        RaceDTO raceDTO = findById(id);
//        return breeders.stream().mapToDouble(loft -> geoUtil.haversine(raceDTO.latitude(), loft.getLatitude())).average().orElse(0.0);
        return 0.0;
    }


    @Override
    public List<Breeder> getParticipatingBreeders(String id) {
        return raceRepository.findDistinctLoftsByRaceId(UUID.fromString(id));

    }
}
