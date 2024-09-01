package com.movieReservation.DTOs.responseDTO;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
public class TicketResponse {
    private String user;
    private String movie;
    private Date movieDate;
    private Time movieTime;
    private String roomName;
    private String bookedSeats;
    private String status;
}
