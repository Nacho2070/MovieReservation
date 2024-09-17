package com.movieReservation.DTOs.requestsDTO;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;
@Data
public class ShowTimeRequestDTO {
    private Time showStartTime;
    private Date showDate;
    private Long roomId;
    private Long movieId;
}

