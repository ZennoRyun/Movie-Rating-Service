package com.example.movierating.service;

import com.example.movierating.entity.BoxOfficeEntity;
import com.example.movierating.persistence.BoxOfficeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BoxOfficeService {

    @Autowired
    private BoxOfficeRepository boxOfficeRepository;

    public BoxOfficeEntity createBoxOffice(final BoxOfficeEntity entity) {
        validate(entity);
        log.info("Entity Cd : {} is saved.", entity.getMovieCd());

        return boxOfficeRepository.save(entity);
    }

    public void deleteBoxOffice() {
        boxOfficeRepository.deleteAll();
    }
    private void validate(final BoxOfficeEntity entity) {
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
