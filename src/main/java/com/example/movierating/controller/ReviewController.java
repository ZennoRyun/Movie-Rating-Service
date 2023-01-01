package com.example.movierating.controller;

import com.example.movierating.dto.ReviewDTO;
import com.example.movierating.entity.ReviewEntity;
import com.example.movierating.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/registerReview")
    public RedirectView registerReview(ReviewDTO dto) {
        ReviewEntity entity = ReviewDTO.toEntity(dto);
        reviewService.registerReview(entity);

        return new RedirectView("/");
    }
}
