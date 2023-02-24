package com.example.movierating.controller;

import com.example.movierating.dto.ReviewDTO;
import com.example.movierating.entity.ReviewEntity;
import com.example.movierating.service.MovieService;
import com.example.movierating.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieService movieService;

    @PostMapping("/registerReview")
    public RedirectView registerReview(@AuthenticationPrincipal String userId, ReviewDTO dto, RedirectAttributes re) {
        dto.setWriter(userId);
        ReviewEntity entity = ReviewDTO.toEntity(dto);
        reviewService.registerReview(entity);
        String movieCd = dto.getMovieCd();
        Double rate = reviewService.retrieveRateAvg(movieCd);
        movieService.updateRate(movieCd, rate);
        re.addAttribute("movieCd", movieCd);

        return new RedirectView("/movie/viewMovieInfo");
    }
}
