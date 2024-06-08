package com.example.todoserver.service;

import com.example.todoserver.domain.User;
import com.example.todoserver.entity.UserEntity;
import com.example.todoserver.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id){
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        return new User(user.getUserNum(), user.getId(), user.getPassword(), user.getEmail());
    }

    public boolean addUser(User user){
        UserEntity newUser = new UserEntity(user.getUserNum(), user.getId(), user.getPassword(), user.getEmail());
        userRepository.save(newUser);
        return true;
    }
}
