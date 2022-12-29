package com.example.movierating.persistence;

import com.example.movierating.entity.BoxOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoxOfficeRepository extends JpaRepository<BoxOfficeEntity, String> {
}
