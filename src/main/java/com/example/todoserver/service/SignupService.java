package com.example.todoserver.service;

import com.example.todoserver.domain.User;
import com.example.todoserver.entity.UserEntity;
import com.example.todoserver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignupService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getUser(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return new User(user);
    }

    public boolean addUser(User user){
        String rawPassword = user.getPassword();
        String encoded = passwordEncoder.encode(rawPassword);

        UserEntity newUser = new UserEntity(user);
        newUser.setPassword(encoded);
        userRepository.save(newUser);
        return true;
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
