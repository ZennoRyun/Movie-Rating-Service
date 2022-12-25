package com.example.movierating;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie {
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String genreNm;
    private String directors;
    private String actors;
    private String image;
}