package com.movieReservation.DTOs.responseDTO;


import com.movieReservation.DTOs.ShowTimeDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MovieResponseDTO {
    private String name;
    private String description;
    private String genre;
    private String posterImage;
    private List<ShowTimeDTO> showTimes;
}
