package com.movieReservation.DTOs.responseDTO;

import com.movieReservation.models.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

//reserve seats for a showtime
@Data
@Builder
public class ReservationResponseDTO {
    private String userName;
    private Status status;
    private Date showTime;
    private String movieName;
    private int seat;
}
