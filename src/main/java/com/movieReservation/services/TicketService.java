package com.movieReservation.services;

import com.movieReservation.utils.mapper;
import com.movieReservation.DTOs.requestsDTO.ReservationRequestDTO;
import com.movieReservation.DTOs.responseDTO.TicketResponse;
import com.movieReservation.exception.exceptions.NotFoundException;
import com.movieReservation.models.*;
import com.movieReservation.models.enums.Status;
import com.movieReservation.services.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.movieReservation.services.repository.ShowTimeRepository;
import com.movieReservation.services.repository.UserRepository;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketService {

    private final ShowTimeRepository showTimeRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    public TicketResponse reserveSeats(ReservationRequestDTO reservationRequest) {
        Optional<ShowTime> showTimeOpt = showTimeRepository.findById(reservationRequest.getShowTimeId());
        Optional<User> userOpt = userRepository.findById(reservationRequest.getUserId());

        if(showTimeOpt.isEmpty() || userOpt.isEmpty()){
            //Need to implement the Custom Exception
            throw new NotFoundException("ShowTime id or user id is not correct");
        }

        User user = userOpt.get();
        ShowTime showTime = showTimeOpt.get();

        if (!isSeatAvailable(reservationRequest.getSeatList(),showTime)){
            throw new EmptyStackException();
        }

        List<ShowSeats> showSeats = setReservesSeats(showTime,reservationRequest.getSeatList());
        String seats = listToString(reservationRequest.getSeatList());

        Ticket ticket = new Ticket ();
                ticket.setUser(user);
                ticket.setBookedSeats(seats);
                ticket.setShowTime(showTime);
                ticket.setStatus(Status.ACTIVE);

        ticketRepository.save(ticket);

        //Update the news Seats
        showTime.setShowSeats(showSeats);
        showTime.setTicket(ticket);
        user.setTicket(ticket);

        showTimeRepository.save(showTime);
        userRepository.save(user);

        return mapper.ticketResponseMap(showTime, ticket);
    }

    private boolean isSeatAvailable(List<String> seatDTO, ShowTime showSeats){

        for(ShowSeats seatsShowTime :showSeats.getShowSeats()){
            String seatNo = seatsShowTime.getSeatNo();
            //If the Seat is not available
            if(seatDTO.contains(seatNo) && !seatsShowTime.getIsAvailable() ){
                return false;
            }
        }
        return true;
    }

    private List<ShowSeats> setReservesSeats( ShowTime showTime, List<String> seatDTO){
        List<ShowSeats> showSeats = new ArrayList<>();
        for (ShowSeats showSeats1 : showTime.getShowSeats()){
            if(seatDTO.contains(showSeats1.getSeatNo())){
                showSeats1.setIsAvailable(Boolean.FALSE);
                showSeats.add(showSeats1);
            }
            showTime.setShowSeats(showSeats);

        }
        return showSeats;
    }
    private String listToString(List<String> requestSeats) {
        StringBuilder sb = new StringBuilder();

        for (String s : requestSeats) {
            sb.append(s).append(",");
        }

        return sb.toString();
    }
}
