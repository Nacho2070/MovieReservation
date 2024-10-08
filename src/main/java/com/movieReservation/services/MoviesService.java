package com.movieReservation.services;

import com.movieReservation.DTOs.responseDTO.SeatDTO;
import com.movieReservation.DTOs.requestsDTO.MovieRequestDTO;
import com.movieReservation.DTOs.responseDTO.MovieResponseDTO;
import com.movieReservation.DTOs.responseDTO.MovieShowTimeResponseDTO;
import com.movieReservation.DTOs.responseDTO.RoomResponseDTO;
import com.movieReservation.utils.mapper;
import com.movieReservation.DTOs.responseDTO.ShowTimeDTO;
import com.movieReservation.exception.exceptions.NotFoundException;
import com.movieReservation.models.ShowTime;
import com.movieReservation.services.repository.ShowTimeRepository;
import lombok.AllArgsConstructor;

import com.movieReservation.DTOs.responseDTO.ReservationResponseDTO;
import com.movieReservation.models.Movies;
import com.movieReservation.models.Ticket;
import com.movieReservation.models.Room;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.movieReservation.services.repository.MovieRepository;
import com.movieReservation.services.repository.ReservationRepository;
import com.movieReservation.services.repository.RoomRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MoviesService {

    private final ShowTimeRepository showTimeRepository;
    private final MovieRepository movieRepository;
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    public ResponseEntity<String> addMovie (MovieRequestDTO movieDto) {

        Movies movies = new Movies();

        movies.setName(movieDto.getName());
        movies.setDescription(movieDto.getDescription());
        movies.setGenre(movieDto.getGenre());
        movies.setPosterImage(movieDto.getPosterImage());

        movieRepository.save(movies);

        return ResponseEntity.ok("Movie added successfully");
    }


    public ResponseEntity<String> updateMovie(Long id, MovieRequestDTO movie) {

            Optional<Movies> optionalMovie = movieRepository.findById(id);
            if(optionalMovie.isPresent()){
                Movies movieEntity = optionalMovie.get();
                    movie.setName(movie.getName());
                    movie.setDescription(movie.getDescription());
                    movie.setGenre(movie.getGenre());
                    movie.setPosterImage(movie.getPosterImage());

                movieRepository.save(movieEntity);
                return ResponseEntity.ok("Movie updated successfully");
            }
        throw  new NotFoundException("Movie with id: "+id+" does not exist");
    }

    public ResponseEntity<String> deleteMovie(Long id) {


            Optional<Movies> moviesOptional = movieRepository.findById(id);
            if(moviesOptional.isPresent()){
                movieRepository.deleteById(id);
                return ResponseEntity.ok("Movie deleted successfully");
            }

            throw new NotFoundException("Movie with id: "+id+" does not exist");
    }

    public List<MovieResponseDTO> getAllMovies() {
        List<Movies> movie = movieRepository.findAll();

        if(movie.isEmpty()){throw new NotFoundException("There not movies");};

        return moviesToMoviesDTO(movie);
    }

    public List<ReservationResponseDTO> getAllReservations() {

        List<Ticket> tickets = reservationRepository.findAll();
        if(tickets != null){
            return mapper.reservationToReservationDto(tickets);
        }
        throw new NotFoundException("Without reservations");

    }
    // Obtener la capacidad total de las salas
    public RoomResponseDTO calculateTotalCapacity(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()-> new NotFoundException("Without reservations"));

        return mapper.roomToDTO(room);
    }

    public List<MovieShowTimeResponseDTO> getMoviesAndShowTimes() {

        List<Movies> moviesAndShowTime = movieRepository.findAll();
        return mapper.getMoviesAndShowTimes(moviesAndShowTime);

    }

    public List<SeatDTO> getAvailableSeats(Long showtimeId) {

        ShowTime showTime = showTimeRepository.findById(showtimeId)
                .orElseThrow( () -> new NotFoundException("Id not found"));


        return mapper.allSeatByShowIdDTO(showTime);
    }

    public List<MovieResponseDTO> moviesToMoviesDTO(List<Movies> movies) {
        return  movies.stream().map( movie ->
                MovieResponseDTO.builder()
                        .name(movie.getName())
                        .description(movie.getDescription())
                        .genre(movie.getGenre())
                        .posterImage(movie.getPosterImage())
                        .showTimes(movie.getShowTime().stream()
                                .map(showTime -> new ShowTimeDTO(showTime.getShowDate(),showTime.getStartTime()))
                                .collect(Collectors.toList()))
                        .build())
                        .collect(Collectors.toList());
    }


}
