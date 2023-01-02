package com.example.movierating.persistence;

import com.example.movierating.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    @Query(value = "SELECT AVG(RATE) FROM REVIEW WHERE MOVIE_CD = :movieCd", nativeQuery = true)
    Double retrieveRateAvg(@Param("movieCd") String movieCd);
}
