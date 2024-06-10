package com.example.todoserver.domain;

import com.example.todoserver.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String userId;
    private String password;
    private String email;

    public User(UserEntity userEntity){
        this.id = userEntity.getId();
        this.userId = userEntity.getUserId();
        this.password = userEntity.getPassword();
        this.email = userEntity.getEmail();
    }
}
