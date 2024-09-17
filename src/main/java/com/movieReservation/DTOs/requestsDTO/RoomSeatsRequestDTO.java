package com.movieReservation.DTOs.requestsDTO;

import lombok.Data;

@Data
public class RoomSeatsRequestDTO {
    private String roomName;
    private Long totalSeats;

}
