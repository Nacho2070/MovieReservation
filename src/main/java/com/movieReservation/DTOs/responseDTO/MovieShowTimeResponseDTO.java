package com.movieReservation.DTOs.responseDTO;



import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class MovieShowTimeResponseDTO {
    private List<MovieResponseDTO> movies;

}
