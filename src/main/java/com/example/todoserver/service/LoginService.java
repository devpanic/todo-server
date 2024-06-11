package com.example.todoserver.service;

import com.example.todoserver.domain.User;
import com.example.todoserver.entity.UserEntity;
import com.example.todoserver.repository.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(String userId) {
        return userRepository.findByUserId(userId)
                .map(User::new);
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<UserEntity> userOptional = userRepository.findByUserId(userId);
        User user = null;

        if(userOptional.isPresent()){
            user = new User(userOptional.get());
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
