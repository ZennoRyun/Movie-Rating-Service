package com.example.movierating.controller;

import com.example.movierating.api.MovieInfoAPI;
import com.example.movierating.dto.MovieDTO;
import com.example.movierating.dto.ResponseDTO;
import com.example.movierating.entity.MovieEntity;
import com.example.movierating.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

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
        MovieEntity movieEntity = MovieEntity.builder()
                .movieCd(movieInfoAPI.getMovieInfo(movieCd).getMovieCd())
                .movieNm(movieInfoAPI.getMovieInfo(movieCd).getMovieNm())
                .openDt(movieInfoAPI.getMovieInfo(movieCd).getOpenDt())
                .genreNm(movieInfoAPI.getMovieInfo(movieCd).getGenreNm())
                .directors(movieInfoAPI.getMovieInfo(movieCd).getDirectors())
                .actors(movieInfoAPI.getMovieInfo(movieCd).getActors())
                .image(movieInfoAPI.getMovieInfo(movieCd).getImage())
                .build();
        movieService.movieCreate(movieEntity);
        model.addAttribute("movie", movieInfoAPI.getMovieInfo(movieCd));
        return "viewMovieInfo";
    }
    /*@PostMapping("viewMovieInfo")
    public ResponseEntity<?> saveMovie(@RequestParam String movieCd, Model model, @RequestBody MovieDTO dto) throws Exception {
        try {
            MovieInfoAPI movieInfoAPI = new MovieInfoAPI();
            MovieEntity movieEntity = MovieEntity.builder()
                    .movieCd(movieInfoAPI.getMovieInfo(movieCd).getMovieCd())
                    .movieNm(movieInfoAPI.getMovieInfo(movieCd).getMovieNm())
                    .openDt(movieInfoAPI.getMovieInfo(movieCd).getOpenDt())
                    .genreNm(movieInfoAPI.getMovieInfo(movieCd).getGenreNm())
                    .directors(movieInfoAPI.getMovieInfo(movieCd).getDirectors())
                    .actors(movieInfoAPI.getMovieInfo(movieCd).getActors())
                    .image(movieInfoAPI.getMovieInfo(movieCd).getImage())
                    .build();
            MovieEntity savedMovie = movieService.movieCreate(movieEntity);
            MovieDTO responseMovieDTO = MovieDTO.builder()
                    .movieCd(savedMovie.getMovieCd())
                    .movieNm(savedMovie.getMovieNm())
                    .openDt(savedMovie.getOpenDt())
                    .genreNm(savedMovie.getGenreNm())
                    .directors(savedMovie.getDirectors())
                    .actors(savedMovie.getActors())
                    .image(savedMovie.getImage())
                    .build();
            return ResponseEntity.ok().body(responseMovieDTO);

            //model.addAttribute("movie", movieInfoAPI.getMovieInfo(movieCd));
            //return "viewMovieInfo";
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }*/
}