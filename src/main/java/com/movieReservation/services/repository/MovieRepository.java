package com.movieReservation.services.repository;

import com.movieReservation.models.Movies;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movies, Long> {
}