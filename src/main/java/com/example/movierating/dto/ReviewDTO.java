package com.example.movierating.dto;

import com.example.movierating.entity.ReviewEntity;
import com.example.movierating.entity.UserEntity;
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

    private UserEntity author;

    public ReviewDTO(final ReviewEntity entity) {
        this.id = entity.getId();
        this.movieCd = entity.getMovieCd();
        this.rate = entity.getRate();
        this.content = entity.getContent();
        this.regDate = entity.getRegDate();
        this.author = entity.getAuthor();
    }

    public static ReviewEntity toEntity(final ReviewDTO dto) {
        return ReviewEntity.builder()
                .id(dto.getId())
                .movieCd(dto.getMovieCd())
                .rate(dto.getRate())
                .content(dto.getContent())
                .regDate(dto.getRegDate())
                .author(dto.getAuthor())
                .build();
    }
}
