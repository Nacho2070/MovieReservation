package com.movieReservation.DTOs.responseDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SeatDTO {
    private String seatNo;
    private boolean isAvailable;
}
