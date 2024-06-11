package com.example.todoserver.controller;

import com.example.todoserver.domain.User;
import com.example.todoserver.service.LoginService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody User user){
        Optional<User> loginUserOpt = loginService.getUser(user.getUserId());
        if(loginUserOpt.isPresent()){
            User loginUser = loginUserOpt.get();

            if (passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) {
                return ResponseEntity.ok(loginUser);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginUser.getUserId());
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
