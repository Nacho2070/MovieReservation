package com.movieReservation.DTOs.responseDTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieResponseDTO {
    private String movieId;
    private String name;
    private String description;
    private String genre;
    private String posterImage;
    private List<ShowTimeDTO> showTimes;
}
