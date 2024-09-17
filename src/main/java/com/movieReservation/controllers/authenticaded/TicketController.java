package com.movieReservation.controllers.authenticaded;

import com.movieReservation.DTOs.requestsDTO.ReservationRequestDTO;
import com.movieReservation.DTOs.responseDTO.TicketResponse;
import com.movieReservation.services.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/ticketReservation")
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/reserveSeats")
    public ResponseEntity<TicketResponse> reserveSeats(@RequestBody ReservationRequestDTO reservationRequest) {
        System.out.println("Ticket sda");
        return ResponseEntity.ok(ticketService.reserveSeats(reservationRequest));
    }

}
