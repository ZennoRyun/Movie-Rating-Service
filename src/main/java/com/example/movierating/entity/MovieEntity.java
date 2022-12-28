package com.example.movierating.entity;

import lombok.*;
import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Movie")
public class MovieEntity {

    @Id
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String genreNm;
    private String directors;
    private String actors;
    private String image;
    @Builder.Default
    private Double rate = 0.0;
}