package com.example.movierating.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "BoxOffice")
public class BoxOfficeEntity {

    @Id
    private String movieCd;
    private String movieNm;
    private String image;
    private Integer ranking;
}
