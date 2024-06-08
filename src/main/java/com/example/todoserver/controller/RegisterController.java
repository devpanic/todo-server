package com.example.todoserver.controller;

import com.example.todoserver.domain.User;
import com.example.todoserver.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @GetMapping("/{id}")
    public User checkUser(@PathVariable Long id){
        return registerService.getUser(id);
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        if(registerService.addUser(user)){
            return user;
        }
        return null;
    }
}
