package com.movieReservation.services;

import com.movieReservation.utils.mapper;
import com.movieReservation.DTOs.requestsDTO.UserTicketRequestDTO;
import com.movieReservation.DTOs.responseDTO.TicketResponse;
import com.movieReservation.models.Ticket;
import com.movieReservation.models.User;
import com.movieReservation.models.enums.Status;
import com.movieReservation.services.repository.TicketRepository;
import com.movieReservation.services.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationsService {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public List<TicketResponse> getAllUserReservations(UserTicketRequestDTO ticketRequest) {

        User user = userRepository.findByNameAndEmail(ticketRequest.getUserName(),ticketRequest.getEmail());

        List<Ticket> ticket = ticketRepository.findByUser(user);

        return mapper.allticketResponseMap(ticket);
    }

    public ResponseEntity<String> cancelReservation(Long ReservationId) {

        Ticket ticket = ticketRepository.findById(ReservationId)
                .orElseThrow(()-> new RuntimeException("Ticket not found"));

        ticket.setStatus(Status.CANCELLED);
        ticketRepository.save(ticket);
        return ResponseEntity.ok("Reservation status change");
    }





}
