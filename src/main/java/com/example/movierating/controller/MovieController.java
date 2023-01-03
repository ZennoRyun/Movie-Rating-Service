package com.example.movierating.controller;

import com.example.movierating.api.MovieInfoAPI;
import com.example.movierating.dto.MovieDTO;
import com.example.movierating.entity.MovieEntity;
import com.example.movierating.entity.ReviewEntity;
import com.example.movierating.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("movie")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/viewMovieInfo")
    public String viewMovieInfo(@RequestParam String movieCd, Model model) throws Exception {
        Optional<MovieEntity> movieEntity = movieService.retrieve(movieCd);
        ReviewEntity reviewEntity = ReviewEntity.builder().build();
        if(movieEntity.isEmpty()) {
            MovieInfoAPI movieInfoAPI = new MovieInfoAPI();
            MovieDTO dto = movieInfoAPI.getMovieInfo(movieCd);
            MovieEntity entity = MovieDTO.toEntity(dto);
            movieService.createMovie(entity);
            model.addAttribute("movie", entity);
            model.addAttribute("review", reviewEntity);
        } else {
            movieEntity.get().setRate(Math.round(movieEntity.get().getRate()*100)/100.0);
            model.addAttribute("movie", movieEntity.get());
            model.addAttribute("review", reviewEntity);
        }
        return "viewMovieInfo";
    }
}