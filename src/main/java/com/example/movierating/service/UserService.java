package com.example.movierating.service;

import com.example.movierating.entity.UserEntity;
import com.example.movierating.exception.DataNotFoundException;
import com.example.movierating.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public UserEntity create(String username, String email, String password) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);

        return user;
    }

    public UserEntity getUser(String username) {
        Optional<UserEntity> user = this.userRepository.findByusername(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new DataNotFoundException("user not found");
        }
    }
}