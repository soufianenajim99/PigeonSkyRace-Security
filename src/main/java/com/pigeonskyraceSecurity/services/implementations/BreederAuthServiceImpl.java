package com.pigeonskyraceSecurity.services.implementations;

import com.pigeonskyraceSecurity.dtos.BreederDTO;
import com.pigeonskyraceSecurity.models.Breeder;
import com.pigeonskyraceSecurity.repositories.BreederRepository;
import com.pigeonskyraceSecurity.services.BreederAuthService;
import com.pigeonskyraceSecurity.utils.PasswordUtil;
import com.pigeonskyraceSecurity.utils.mappers.dtos.BreederMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreederAuthServiceImpl implements BreederAuthService {
    @Autowired
    private BreederRepository breederRepository;

    @Autowired
    private BreederMapper breederMapper;


    @Override
    public String register(Breeder breeder) {
        breeder.setPassword(PasswordUtil.hashPassword(breeder.getPassword()));
        breederRepository.save(breeder);
        return "Breeder registered successfully";
    }

    @Override
    public boolean login(String username, String password) {
        Breeder breeder = breederRepository.findByUsername(username);
        if (breeder != null) {
            return PasswordUtil.verifyPassword(password, breeder.getPassword());
        }
        return false;
    }

    @Override
    public List<BreederDTO> findAll() {
        return breederRepository.findAll().stream()
                .map(breederMapper::toDTO)
                .toList();
    }

}
