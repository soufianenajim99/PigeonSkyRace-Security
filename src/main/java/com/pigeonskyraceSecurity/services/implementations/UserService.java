package com.pigeonskyraceSecurity.services.implementations;


import com.pigeonskyraceSecurity.models.User;
import com.pigeonskyraceSecurity.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

}