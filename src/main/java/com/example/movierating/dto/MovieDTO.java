package com.example.movierating.dto;

import com.example.movierating.entity.MovieEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovieDTO {
    private String movieCd;
    private String movieNm;
    private String openDt;
    private String genreNm;
    private String directors;
    private String actors;
    private String image;
    private Double rate;
    public MovieDTO(final MovieEntity entity) {
        this.movieCd = entity.getMovieCd();
        this.movieNm = entity.getMovieNm();
        this.openDt = entity.getOpenDt();
        this.genreNm = entity.getGenreNm();
        this.directors = entity.getDirectors();
        this.actors = entity.getActors();
        this.image = entity.getImage();
        this.rate = entity.getRate();
    }
    public static MovieEntity toEntity(final MovieDTO dto) {
        return MovieEntity.builder()
                .movieCd(dto.getMovieCd())
                .movieNm(dto.getMovieNm())
                .openDt(dto.getOpenDt())
                .genreNm(dto.getGenreNm())
                .directors(dto.getDirectors())
                .actors(dto.getActors())
                .image(dto.getImage())
                .rate(dto.getRate())
                .build();
    }
}