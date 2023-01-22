package com.example.movierating.persistence;

import com.example.movierating.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    List<ReviewEntity> findByMovieCd(String movieCd);

    @Query(value = "SELECT AVG(RATE) FROM review WHERE MOVIE_CD = :movieCd", nativeQuery = true)
    Double retrieveRateAvg(@Param("movieCd") String movieCd);
}
