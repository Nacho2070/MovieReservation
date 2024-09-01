package com.movieReservation.DTOs;

import com.movieReservation.DTOs.responseDTO.*;
import com.movieReservation.models.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class mapper {

    public static RoomResponseDTO roomToDTO(Room room) {
        RoomResponseDTO roomDTO = new RoomResponseDTO();

        List<SeatDTO> seatDTOs = room.getSeats().stream()
                .map(mapper::mapToSeatDTO)
                .collect(Collectors.toList());

        roomDTO.setSeats(seatDTOs);

        roomDTO.setRoomName(room.getRoomName());
        roomDTO.setCapacity(room.getCapacity());

        return roomDTO;
    }
    public static SeatDTO mapToSeatDTO(Seats seat) {
        return  SeatDTO.builder()
                .seatNo(seat.getSeatNo())
                .isAvailable(seat.getIsAvailable())
                .build();
    }
    public static List<SeatDTO> allSeatByShowIdDTO(ShowTime showTime) {

        return showTime.getShowSeats().stream()
                .map(seat ->
                        SeatDTO.builder()
                        .seatNo(seat.getSeatNo())
                        .isAvailable(seat.getIsAvailable())
                        .build())
                .collect(Collectors.toList());

    }
    public static List<ReservationResponseDTO> reservationToReservationDto(List<Ticket> tickets) {
        return tickets.stream()
                .map(ticket -> mapToDto(ticket))
                .collect(Collectors.toList());
    }

    private static ReservationResponseDTO mapToDto(Ticket ticket) {
        return ReservationResponseDTO.builder()
                .userName(ticket.getUser().getName())
                .showTime(ticket.getShowTime().getStartTime())
                .status(ticket.getStatus())
                .movieName(ticket.getShowTime().getMovie().getName())
                .seat(ticket.getShowTime().getShowSeats().size())
                .build();
    }


    public static List<MovieShowTimeResponseDTO> getMoviesAndShowTimes(List<Movies> moviesAndShowTime) {

            List<MovieShowTimeResponseDTO> movieShowTimeDTOList = new ArrayList<>();

        for (Movies movie : moviesAndShowTime) {
            List<ShowTimeDTO> showTimeDTOList = new ArrayList<>();
            for (ShowTime showTime : movie.getShowTime()) {
                ShowTimeDTO showTimeDTO = ShowTimeDTO.builder()
                        .showDate(showTime.getShowDate())
                        .build();
                showTimeDTOList.add(showTimeDTO);
            }

            MovieResponseDTO movieResponseDTO = MovieResponseDTO.builder()
                    .name(movie.getName())
                    .description(movie.getDescription())
                    .genre(movie.getGenre())
                    .posterImage(movie.getPosterImage())
                    .showTimes(showTimeDTOList)
                    .build();

            MovieShowTimeResponseDTO movieShowTimeDTO = MovieShowTimeResponseDTO.builder()
                    .movies(Collections.singletonList(movieResponseDTO))
                    .build();

            movieShowTimeDTOList.add(movieShowTimeDTO);
        }

            return movieShowTimeDTOList;

    }
    public static TicketResponse ticketResponseMap(ShowTime showTime, Ticket ticket) {
        return TicketResponse.builder()
                .user(ticket.getUser().getName())
                .movie(showTime.getMovie().getName())
                .movieDate(showTime.getShowDate())
                .movieTime(showTime.getStartTime())
                .roomName(showTime.getRoom().getRoomName())
                .bookedSeats(ticket.getBookedSeats())
                .status(ticket.getStatus().toString())
                .build();
    }



        public static List<TicketResponse> allticketResponseMap(List<Ticket> tickets) {
            return tickets.stream()
                    .map(ticket -> TicketResponse.builder()
                            .user(ticket.getUser().getName() + " " + ticket.getUser().getLastName())
                            .movie(ticket.getShowTime().getMovie().getName())
                            .movieDate(ticket.getShowTime().getShowDate())
                            .movieTime(ticket.getShowTime().getStartTime())
                            .roomName(ticket.getShowTime().getRoom().getRoomName())
                            .bookedSeats(ticket.getBookedSeats())
                            .status(ticket.getStatus().toString())
                            .build())
                    .collect(Collectors.toList());
        }




}
