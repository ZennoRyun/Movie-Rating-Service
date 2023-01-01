package com.example.movierating.controller;

import com.example.movierating.entity.BoxOfficeEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping
    public String main(){
        return "main";
    }
}
