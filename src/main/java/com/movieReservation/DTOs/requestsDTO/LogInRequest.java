package com.movieReservation.DTOs.requestsDTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogInRequest {
    private String email;
    private String password;
}
