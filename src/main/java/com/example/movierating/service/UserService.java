package com.example.movierating.service;

import com.example.movierating.entity.UserEntity;
import com.example.movierating.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity) {
        if(userEntity == null || userEntity.getUserid() == null ) {
            throw new RuntimeException("Invalid arguments");
        }
        final String userid = userEntity.getUserid();
        if(userRepository.existsByUserid(userid)) {
            log.warn("Userid already exists {}", userid);
            throw new RuntimeException("Userid already exists");
        }

        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String userid, final String password, final PasswordEncoder encoder) {
        final UserEntity originalUser = userRepository.findByUserid(userid);

        // matches 메서드를 이용해 패스워드가 같은지 확인
        if(originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }
        return null;
    }
}