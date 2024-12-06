package com.pigeonskyraceSecurity.services.implementations;


import com.opencsv.bean.CsvToBeanBuilder;
import com.pigeonskyraceSecurity.dtos.BreederDTO;
import com.pigeonskyraceSecurity.dtos.PigeonDTO;
import com.pigeonskyraceSecurity.dtos.RaceDTO;
import com.pigeonskyraceSecurity.dtos.RankingDTO;
import com.pigeonskyraceSecurity.models.Ranking;
import com.pigeonskyraceSecurity.repositories.RankingRepository;
import com.pigeonskyraceSecurity.services.BreederAuthService;
import com.pigeonskyraceSecurity.services.PigeonService;
import com.pigeonskyraceSecurity.services.RaceService;
import com.pigeonskyraceSecurity.services.RankingService;
import com.pigeonskyraceSecurity.utils.mappers.dtos.PigeonMapper;
import com.pigeonskyraceSecurity.utils.mappers.dtos.RaceMapper;
import com.pigeonskyraceSecurity.utils.mappers.dtos.RankingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.UUID;

@Service
public class RankingServiceImpl implements RankingService {

    private final RankingRepository rankingRepository;
    private final RankingMapper rankingMapper;
    private final PigeonMapper pigeonMapper;
    private final PigeonService pigeonService;
    private final BreederAuthService breederAuthService;
    private final RaceService raceService;
    private final RaceMapper raceMapper;

    @Autowired
    public RankingServiceImpl(RaceService raceService,RaceMapper raceMapper,PigeonMapper pigeonMapper, RankingRepository rankingRepository,RankingMapper rankingMapper,PigeonService pigeonService,BreederAuthService breederAuthService) {
        this.rankingRepository = rankingRepository;
        this.rankingMapper = rankingMapper;
        this.pigeonService = pigeonService;
        this.pigeonMapper = pigeonMapper;
        this.breederAuthService = breederAuthService;
        this.raceService = raceService;
        this.raceMapper = raceMapper;
    }

    public RankingDTO addPigeonToRace(RankingDTO rankingDTO) {
        Ranking ranking = rankingMapper.toRanking(rankingDTO);
        Ranking savedRanking = rankingRepository.save(ranking);
        return rankingMapper.toDTO(savedRanking);
    }


    @Override
    public List<RankingDTO> saveAll(File csvFile) {
        List<Ranking> rankings = convertCSV(csvFile);
        List<BreederDTO> breeders = breederAuthService.findAll();
        int breederCount = breeders.size();
        int[] breederIndex = {0};
        rankings.forEach(ranking -> {
            if (ranking.getPigeon() != null) {
                String breederId = String.valueOf(breeders.get(breederIndex[0] % breederCount).id());
                breederIndex[0]++;
                pigeonService.save(
                        pigeonMapper.toDTO(ranking.getPigeon()).withBreederId(UUID.fromString(breederId))
                );
            }
        });






        List<RaceDTO> races = raceService.findAll();
        List<PigeonDTO> pigeons = pigeonService.findAll();
        int raceCount = races.size();
        int pigeonCount = pigeons.size();
        return rankings.stream()
                .map(ranking -> {
                    // Determine the current pigeon and race in a round-robin manner
                    int index = rankings.indexOf(ranking);
                    UUID pigeonId = UUID.fromString(String.valueOf(pigeons.get(index % pigeonCount).id()));
                    String raceId = String.valueOf(races.get(index % raceCount).id());

                    // Map ranking and assign pigeon and race IDs
                    return rankingMapper.toDTO(ranking)
                            .withPigeonAndRaceIds(pigeonId, UUID.fromString(raceId));
                })
                .map(this::addPigeonToRace)
                .toList();
    }


    @Override
    public List<Ranking> convertCSV(File csvFile){
        try {
            List<Ranking> rankings = new CsvToBeanBuilder<Ranking>(new FileReader(csvFile)).withType(Ranking.class).build().parse();
            return rankings;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
