package com.movieReservation.controllers.authenticaded;

import com.movieReservation.DTOs.requestsDTO.UserTicketRequestDTO;
import com.movieReservation.DTOs.responseDTO.MovieShowTimeResponseDTO;
import com.movieReservation.DTOs.responseDTO.TicketResponse;
import com.movieReservation.services.MoviesService;
import com.movieReservation.services.reservationService.ReservationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationsService reservationsService;
    private final MoviesService moviesService;


    @GetMapping("/userReserves")
    public ResponseEntity<List<TicketResponse>> getUserReservations(@RequestBody UserTicketRequestDTO seeReservationsDto) {
        List<TicketResponse> reservations = reservationsService.getAllUserReservations(seeReservationsDto);
        return ResponseEntity.ok(reservations);
    }

    @DeleteMapping("/cancelReservation/{reservationId}")
    public ResponseEntity<String> cancelReservation(@PathVariable Long reservationId) {
        return reservationsService.cancelReservation(reservationId);
    }

    @GetMapping("/moviesAndShowtimes")
    public ResponseEntity<List<MovieShowTimeResponseDTO>> getMoviesAndShowTimes() {
        return ResponseEntity.ok(moviesService.getMoviesAndShowTimes());
    }

}
