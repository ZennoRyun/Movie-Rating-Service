package com.example.movierating.persistence;

import com.example.movierating.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, String> {
    //List<MovieEntity> findByMovieCd(String movieCd);
    /*@Query(value = "SELECT RATE FROM MOVIE WHERE movicCd = :movieCd", nativeQuery = true)
    Double findRate(String movieCd);*/

}
