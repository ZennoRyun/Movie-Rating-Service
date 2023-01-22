package com.example.movierating.persistence;

import com.example.movierating.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE movie SET RATE = :rate WHERE MOVIE_CD = :movieCd", nativeQuery = true)
    void updateRate(@Param("movieCd") String movieCd, @Param("rate") Double rate);
}
