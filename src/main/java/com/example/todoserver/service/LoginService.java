package com.example.todoserver.service;

import com.example.todoserver.domain.User;
import com.example.todoserver.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String userId) {
        return userRepository.findByUserId(userId)
                .map(User::new);
    }
}
