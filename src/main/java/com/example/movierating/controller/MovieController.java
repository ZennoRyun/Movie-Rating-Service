package com.example.movierating.controller;

import com.example.movierating.api.MovieInfoAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class MovieController {

    @GetMapping
    public String main(){
        return "main";
    }
    @GetMapping("viewBoxOffice")
    public String viewBoxOffice(Model model) throws Exception {
        MovieInfoAPI movieInfoAPI = new MovieInfoAPI();
        model.addAttribute("dailyBoxOfficeArr", movieInfoAPI.getBoxOffice());

        return "viewBoxOffice";
    }

    @GetMapping("viewMovieInfo")
    public String viewMovieInfo(@RequestParam String movieCd, Model model) throws Exception {
        MovieInfoAPI movieInfoAPI = new MovieInfoAPI();
        model.addAttribute("movie", movieInfoAPI.getMovieInfo(movieCd));
        return "viewMovieInfo";
    }
}