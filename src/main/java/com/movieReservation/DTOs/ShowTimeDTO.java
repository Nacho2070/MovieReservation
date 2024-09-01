package com.movieReservation.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


import java.sql.Time;
import java.sql.Date;
@Data
@Builder
@AllArgsConstructor
public class ShowTimeDTO {
    private Date showDate;
    private Time startTime;
}
