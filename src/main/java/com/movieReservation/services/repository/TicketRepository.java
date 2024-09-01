package com.movieReservation.services.repository;

import com.movieReservation.models.Ticket;
import com.movieReservation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByUser(User userId);
}
