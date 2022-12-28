package com.example.movierating.service;

import com.example.movierating.entity.MovieEntity;
import com.example.movierating.persistence.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Slf4j
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public MovieEntity movieCreate(final MovieEntity entity) {
        validate(entity);
        log.info("Entity Cd : {} is saved.", entity.getMovieCd());

        return movieRepository.save(entity);
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
    /*
    public Double findRate(String movieCd) {
        return movieRepository.findRate(movieCd);
    }*/
}
