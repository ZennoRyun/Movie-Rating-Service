package com.example.movierating.controller;

import com.example.movierating.api.MovieInfoAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class BoxOfficeController {

    @GetMapping
    public String main(){
        return "main";
    }
    @GetMapping("viewBoxoffice")
    public String view(Model model) throws Exception {
        MovieInfoAPI movieInfoAPI = new MovieInfoAPI();
        model.addAttribute("dailyBoxOfficeArr", movieInfoAPI.getMovieInfo());

        return "view";
    }
}