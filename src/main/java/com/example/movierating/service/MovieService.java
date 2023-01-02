package com.example.movierating.service;

import com.example.movierating.entity.MovieEntity;
import com.example.movierating.persistence.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Slf4j
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public void createMovie(final MovieEntity entity) {
        validate(entity);
        log.info("Entity Cd : {} is saved.", entity.getMovieCd());
        movieRepository.save(entity);
    }

    public Optional<MovieEntity> retrieve(String movieCd) {
        return movieRepository.findById(movieCd);
    }

    public void updateRate(String movieCd, Double rate) {
        movieRepository.updateRate(movieCd, rate);
    }

    private void validate(final MovieEntity entity) {
        if(entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getMovieCd() == null) {
            log.warn("Unknown movie.");
            throw new RuntimeException("Unknown movie.");
        }
    }
}
