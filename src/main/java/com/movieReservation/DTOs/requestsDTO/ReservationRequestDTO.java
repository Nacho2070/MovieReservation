package com.movieReservation.DTOs.requestsDTO;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@Builder
public class ReservationRequestDTO {
    private Long userId;
    private Long ShowTimeId;
    private List<String> seatList;
}
