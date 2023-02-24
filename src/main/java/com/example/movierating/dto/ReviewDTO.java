package com.example.movierating.dto;

import com.example.movierating.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReviewDTO {

    private Long id;
    private String movieCd;
    private Double rate;
    private String content;
    private LocalDateTime regDate;
    private String writer;

    public ReviewDTO(final ReviewEntity entity) {
        this.id = entity.getId();
        this.movieCd = entity.getMovieCd();
        this.rate = entity.getRate();
        this.content = entity.getContent();
        this.regDate = entity.getRegDate();
        this.writer = entity.getWriter();
    }

    public static ReviewEntity toEntity(final ReviewDTO dto) {
        return ReviewEntity.builder()
                .id(dto.getId())
                .movieCd(dto.getMovieCd())
                .rate(dto.getRate())
                .content(dto.getContent())
                .regDate(dto.getRegDate())
                .writer(dto.getWriter())
                .build();
    }
}
