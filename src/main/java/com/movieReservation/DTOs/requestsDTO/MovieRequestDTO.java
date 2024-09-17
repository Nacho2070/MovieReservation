package com.movieReservation.DTOs.requestsDTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRequestDTO {
    private String name;
    private String description;
    private String genre;
    private String posterImage;
}
