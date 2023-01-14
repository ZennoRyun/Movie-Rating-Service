package com.example.movierating.service;

import com.example.movierating.entity.BoxOfficeEntity;
import com.example.movierating.entity.ReviewEntity;
import com.example.movierating.persistence.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public void registerReview(ReviewEntity entity) {
        log.info("Entity Cd : {} is saved.", entity.getId());
        reviewRepository.save(entity);
    }

    public List<ReviewEntity> retrieveByMovieCd(String movieCd) {
        return reviewRepository.findByMovieCd(movieCd);
    }

    public Double retrieveRateAvg(String movieCd) {
        return reviewRepository.retrieveRateAvg(movieCd);
    }


}
