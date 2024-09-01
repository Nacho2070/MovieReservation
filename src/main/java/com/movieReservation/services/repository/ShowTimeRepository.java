package com.movieReservation.services.repository;

import com.movieReservation.models.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowTimeRepository extends JpaRepository<ShowTime,Long> {
}
