package com.pigeonskyraceSecurity.services;

import com.pigeonskyraceSecurity.dtos.PigeonDTO;

import java.util.List;

public interface PigeonService {
    PigeonDTO save(PigeonDTO pigeonDTO);
    List<PigeonDTO> saveAll(List<PigeonDTO> pigeonsDTOs);
    List<PigeonDTO> findAll();
    PigeonDTO findById(String id);
}
