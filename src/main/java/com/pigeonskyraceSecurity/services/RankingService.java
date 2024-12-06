package com.pigeonskyraceSecurity.services;

import com.pigeonskyraceSecurity.dtos.RankingDTO;
import com.pigeonskyraceSecurity.models.Ranking;

import java.io.File;
import java.util.List;

public interface RankingService {
    public RankingDTO addPigeonToRace(RankingDTO rankingDTO);
    public List<RankingDTO> saveAll(File csvFile);

    List<Ranking>  convertCSV(File file);
}
