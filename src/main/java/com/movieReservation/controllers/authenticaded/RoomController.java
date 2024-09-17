package com.movieReservation.controllers.authenticaded;

import com.movieReservation.DTOs.requestsDTO.CinemaRoomRequestDTO;
import com.movieReservation.DTOs.requestsDTO.RoomSeatsRequestDTO;
import com.movieReservation.services.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private RoomService roomService;

    @PostMapping("/addRoom")
    public ResponseEntity<String> CinemaRoom(@RequestBody CinemaRoomRequestDTO cinemaRoomRequestDTO) {
        return ResponseEntity.ok(roomService.addCinemaRoom(cinemaRoomRequestDTO));
    }

    @PostMapping("/addSeats")
    public ResponseEntity<String> AddSeats(@RequestBody RoomSeatsRequestDTO seatsRequestDTO) {
        return ResponseEntity.ok(roomService.addSeatsToCinemaRoom(seatsRequestDTO));
    }
}
