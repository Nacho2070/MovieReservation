package com.movieReservation.services.repository;

import com.movieReservation.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findAll();
}
