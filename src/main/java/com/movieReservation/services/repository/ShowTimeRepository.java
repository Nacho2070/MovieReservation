package com.movieReservation.services.repository;

import com.movieReservation.models.ShowTime;
import com.movieReservation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime,Long> {

}
