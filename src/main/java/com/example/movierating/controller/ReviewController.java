package com.example.movierating.controller;

import com.example.movierating.dto.ReviewDTO;
import com.example.movierating.entity.ReviewEntity;
import com.example.movierating.entity.UserEntity;
import com.example.movierating.service.MovieService;
import com.example.movierating.service.ReviewService;
import com.example.movierating.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;

@Controller
@RequestMapping("review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/registerReview")
    public RedirectView registerReview(Principal principal, ReviewDTO dto, RedirectAttributes re) {
        UserEntity user = userService.getUser(principal.getName());
        dto.setAuthor(user);
        ReviewEntity entity = ReviewDTO.toEntity(dto);
        reviewService.registerReview(entity);
        String movieCd = dto.getMovieCd();
        Double rate = reviewService.retrieveRateAvg(movieCd);
        movieService.updateRate(movieCd, rate);
        re.addAttribute("movieCd", movieCd);

        return new RedirectView("/movie/viewMovieInfo");
    }
}