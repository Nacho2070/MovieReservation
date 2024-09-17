package org.mock.services;

import com.movieReservation.DTOs.requestsDTO.MovieRequestDTO;
import com.movieReservation.DTOs.responseDTO.MovieResponseDTO;
import com.movieReservation.DTOs.responseDTO.ReservationResponseDTO;
import com.movieReservation.exception.exceptions.NotFoundException;
import com.movieReservation.models.Movies;
import com.movieReservation.models.Ticket;
import com.movieReservation.services.MoviesService;
import com.movieReservation.services.repository.MovieRepository;
import com.movieReservation.services.repository.ReservationRepository;
import com.movieReservation.services.repository.RoomRepository;
import com.movieReservation.services.repository.ShowTimeRepository;
import com.movieReservation.utils.mapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mock.DataProvider;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.awaitility.Awaitility.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MoviesServiceTest {
    @Mock
    private ShowTimeRepository showTimeRepository;
    @Mock
    private MovieRepository movieRepository;
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private MoviesService moviesService;
    @Test
    void addMovie() {
        MovieRequestDTO movieRequest = DataProvider.movieDTO();
        Movies movie = DataProvider.movieEntity();

        // When
        when(movieRepository.save(any( Movies.class ))).thenReturn( movie );

        ResponseEntity<String> response = moviesService.addMovie(movieRequest);

        Assertions.assertEquals(movieRequest.getName(),movie.getName());
        Assertions.assertNotNull(response);
      //  Assertions.assertNotNull(movieRepository.count(1),count(1)"The repository is null");
    }

    @Test
    void updateMovie() {
        MovieRequestDTO movieRequest = DataProvider.movieDTO();
        Movies movie = DataProvider.movieEntity();

        when(movieRepository.findById(any( Long.class ))).thenReturn(Optional.of(movie));
        when(movieRepository.save(any( Movies.class ))).thenReturn( movie );

        ResponseEntity<String> response = moviesService.updateMovie(1L,movieRequest);
        //Assertions.assertThrows(NotFoundException.class);
        Assertions.assertNotEquals(movie.getName(),movieRequest.getName());
        Assertions.assertNotNull(response);


    }

    @Test
    void deleteMovie() {
        // Given
        Long movieId = 1L;
        Movies movie = DataProvider.movieEntity();

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));

        // When
        ResponseEntity<String> response = moviesService.deleteMovie(movieId);

        // Then
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Movie deleted successfully", response.getBody());
        verify(movieRepository, times(1)).deleteById(movieId);
    }

    @Test
    void deleteMovie_NotFound() {
        // Given
        Long movieId = 1L;

        when(movieRepository.findById(movieId)).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(NotFoundException.class, () -> moviesService.deleteMovie(movieId));
        verify(movieRepository, times(0)).deleteById(movieId);
    }


    @Test
    void getAllMovies() {
        // Given
        List<Movies> movieList = List.of(DataProvider.movieEntity());

        when(movieRepository.findAll()).thenReturn(movieList);

        // When
        List<MovieResponseDTO> movieResponseList = moviesService.getAllMovies();

        // Then
        Assertions.assertNotNull(movieResponseList);
        Assertions.assertFalse(movieResponseList.isEmpty());
        Assertions.assertEquals(movieList.size(), movieResponseList.size());
    }

    @Test
    void getAllMovies_NotFound() {
        // Given
        when(movieRepository.findAll()).thenReturn(Collections.emptyList());

        // Then
        Assertions.assertThrows(NotFoundException.class, () -> moviesService.getAllMovies());
    }

    @Test
    void getAllReservations() {
        // Given
        List<Ticket> ticketList = List.of(DataProvider.ticketEntity());

        when(reservationRepository.findAll()).thenReturn(ticketList);
        when(mapper.reservationToReservationDto(ticketList)).thenReturn(DataProvider.reservationDTOList());

        // When
        List<ReservationResponseDTO> reservationList = moviesService.getAllReservations();

        // Then
        Assertions.assertNotNull(reservationList);
        Assertions.assertFalse(reservationList.isEmpty());
        Assertions.assertEquals(ticketList.size(), reservationList.size());
    }

    @Test
    void getAllReservations_NotFound() {
        // Given
        when(reservationRepository.findAll()).thenReturn(null);

        // Then
        Assertions.assertThrows(NotFoundException.class, () -> moviesService.getAllReservations());
    }

}