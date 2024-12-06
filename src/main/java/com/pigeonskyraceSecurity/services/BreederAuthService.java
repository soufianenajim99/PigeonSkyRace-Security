package com.pigeonskyraceSecurity.services;



import com.pigeonskyraceSecurity.dtos.BreederDTO;
import com.pigeonskyraceSecurity.models.Breeder;

import java.util.List;

public interface BreederAuthService {
    String register(Breeder breeder);
    boolean login(String username, String password);
    List<BreederDTO> findAll();
}
