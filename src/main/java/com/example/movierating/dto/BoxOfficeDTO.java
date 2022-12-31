package com.example.movierating.dto;

import com.example.movierating.entity.BoxOfficeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoxOfficeDTO {

    private String movieCd;
    private String movieNm;
    private String image;
    private Integer ranking;

    public BoxOfficeDTO(final BoxOfficeEntity entity) {
        this.movieCd = entity.getMovieCd();
        this.movieNm = entity.getMovieNm();
        this.image = entity.getImage();
        this.ranking = entity.getRanking();
    }

    public static BoxOfficeEntity toEntity(final BoxOfficeDTO dto) {
        return BoxOfficeEntity.builder()
                .movieCd(dto.getMovieCd())
                .movieNm(dto.getMovieNm())
                .image(dto.getImage())
                .ranking(dto.getRanking())
                .build();
    }
}
