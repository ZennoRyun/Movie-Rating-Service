package com.example.movierating.controller;

import com.example.movierating.dto.ResponseDTO;
import com.example.movierating.dto.UserDTO;
import com.example.movierating.entity.UserEntity;
import com.example.movierating.sercurity.TokenProvider;
import com.example.movierating.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenProvider tokenProvider;

    // Bean으로 작성해도 됨.
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/signUp")
    public String registerUser() {

        return "signUp";
    }
    @PostMapping("/signUp")
    public String registerUser(UserDTO userDTO) {
        try {
            // 리퀘스트를 이용해 저장할 유저 만들기
            UserEntity user = UserEntity.builder()
                    .userid(userDTO.getUserid())
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();
            // 서비스를 이용해 리파지토리에 유저 저장
            userService.create(user);

            return "signIn";
        } catch (Exception e) {
            // 예외가 나는 경우 bad 리스폰스 리턴.
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error(e.getMessage())
                    .build();

            return "signUp";
        }
    }
    @GetMapping("/signIn")
    public String authenticate() {

        return "signIn";
    }
    @PostMapping("/signIn")
    public RedirectView authenticate(UserDTO userDTO) {
        UserEntity user = userService.getByCredentials(
                userDTO.getUserid(),
                userDTO.getPassword(),
                passwordEncoder);

        if(user != null) {
            // 토큰 생성
            final String token = tokenProvider.create(user);
            log.info(token);
            final UserDTO responseUserDTO = UserDTO.builder()
                    .userid(user.getUserid())
                    .uuid(user.getUuid())
                    .token(token)
                    .build();

            return new RedirectView("/");
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login failed.")
                    .build();

            return new RedirectView("/auth/signIn");
        }
    }
}