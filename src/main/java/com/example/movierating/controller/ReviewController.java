package com.example.movierating.controller;

import com.example.movierating.dto.ReviewDTO;
import com.example.movierating.entity.ReviewEntity;
import com.example.movierating.service.MovieService;
import com.example.movierating.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@Controller
@RequestMapping("review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieService movieService;

    @PostMapping("/registerReview")
    public RedirectView registerReview(ReviewDTO dto) {
        ReviewEntity entity = ReviewDTO.toEntity(dto);
        reviewService.registerReview(entity);
        String movieCd = dto.getMovieCd();
        Double rate = reviewService.retrieveRateAvg(movieCd);
        movieService.updateRate(movieCd, rate);
        return new RedirectView("/");
    }
}
