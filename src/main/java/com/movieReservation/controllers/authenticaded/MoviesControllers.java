package com.movieReservation.controllers.authenticaded;

import com.movieReservation.DTOs.requestsDTO.MovieRequestDTO;
import com.movieReservation.DTOs.responseDTO.ReservationResponseDTO;
import com.movieReservation.DTOs.responseDTO.RoomResponseDTO;
import com.movieReservation.DTOs.responseDTO.MovieResponseDTO;
import com.movieReservation.services.MoviesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/movies")
public class MoviesControllers {

    private final MoviesService moviesService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieRequestDTO movie) {
        return moviesService.addMovie(movie);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Long id, @RequestBody MovieRequestDTO movie) {
        return moviesService.updateMovie(id, movie);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        return moviesService.deleteMovie(id);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies() {
        return ResponseEntity.ok(moviesService.getAllMovies());
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseDTO>> getAllReservations() {
        return ResponseEntity.ok(moviesService.getAllReservations());
    }

    @GetMapping("/capacity/{id}")
    public ResponseEntity<RoomResponseDTO> getTotalCapacity(@PathVariable Long id ) {
        return ResponseEntity.ok(moviesService.calculateTotalCapacity(id));
    }

}
