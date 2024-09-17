package com.movieReservation.services;

import com.movieReservation.DTOs.requestsDTO.ShowTimeRequestDTO;
import com.movieReservation.exception.exceptions.NotFoundException;
import com.movieReservation.models.Movies;
import com.movieReservation.models.Room;
import com.movieReservation.models.ShowTime;
import com.movieReservation.services.repository.MovieRepository;
import com.movieReservation.services.repository.RoomRepository;
import com.movieReservation.services.repository.ShowTimeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class ShowTimeService {

        private final ShowTimeRepository showTimeRepository;
        private final MovieRepository movieRepository;
        private final RoomRepository roomRepository;

        public String addShow(ShowTimeRequestDTO showTimeRequestDTO){
            Optional<Movies> movieOpt = movieRepository.findById(showTimeRequestDTO.getMovieId());
            Optional<Room> roomOpt = roomRepository.findById(showTimeRequestDTO.getRoomId());
            log.info(showTimeRequestDTO);
            if(movieOpt.isEmpty() || roomOpt.isEmpty()){
                throw new NotFoundException("Movie or room does´t exist");
            }

            Movies movie = movieOpt.get();
            Room room = roomOpt.get();

            ShowTime showTime = new ShowTime();
                showTime.setShowDate(showTimeRequestDTO.getShowDate());
                showTime.setStartTime(showTimeRequestDTO.getShowStartTime());

                showTime.setMovie(movie);
                showTime.setRoom(room);

            ShowTime updatedShowTime = showTimeRepository.save(showTime);

            movie.getShowTime().add(updatedShowTime);
            room.getShowTimes().add(updatedShowTime);

            movieRepository.save(movie);
            roomRepository.save(room);

            return "Show time added";
        }
}
