package com.movieReservation.DTOs.responseDTO;

import lombok.Data;

import java.util.List;
@Data
public class RoomResponseDTO {
    private String roomName;
    private Long capacity;
    private List<SeatDTO> seats;

}
