package org.mock;

import com.movieReservation.DTOs.requestsDTO.LogInRequest;
import com.movieReservation.DTOs.requestsDTO.MovieRequestDTO;
import com.movieReservation.DTOs.requestsDTO.UserRegisterRequest;
import com.movieReservation.DTOs.responseDTO.ReservationResponseDTO;
import com.movieReservation.models.Movies;
import com.movieReservation.models.Roles;
import com.movieReservation.models.Ticket;
import com.movieReservation.models.User;
import com.movieReservation.models.enums.RoleEnum;
import com.movieReservation.models.enums.Status;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataProvider {



    public static UserRegisterRequest newUserRegisterDTO(){
        List<RoleEnum> roles = new ArrayList<>();
        roles.add(RoleEnum.USER);
        UserRegisterRequest user = new UserRegisterRequest();
            user.setName("Ignacio");
            user.setLastName("Jhon");
            user.setEmail("exampleEmail@outlook.");
            user.setPassword("PasswordTest");
            user.setRol(roles);
        return user;
    }
    public static LogInRequest newUserLogInDTO(){

        LogInRequest inRequest = new LogInRequest();
        inRequest.setEmail("exampleEmail@outlook.");
        inRequest.setPassword("PasswordTest");

        return inRequest;
    }
    public static User newUser(){
        Set<Roles> roles = new HashSet<>();

        Roles userRole = new Roles();
        userRole.setRoles(RoleEnum.USER);
        roles.add(userRole);

        User user = new User();
        user.setName("Ignacio");
        user.setLastName("Jhon");
        user.setEmail("exampleEmail@outlook.");
        user.setPassword("PasswordTest");
        user.setRol(roles);
        return user;
    }
    public static MovieRequestDTO movieDTO(){
         MovieRequestDTO movieDTO = MovieRequestDTO.builder()
                .name("MovieTest")
                .description("This is a movie test")
                .genre("Test")
                .posterImage("Image test")
                .build();
        return movieDTO;
    }

    public static Movies movieEntity() {
        Movies movies = new Movies();
            movies.setName("MovieTest");
            movies.setDescription("This is a movie description");
            movies.setGenre("Test");
            movies.setPosterImage("Image test");
        return movies;
    }

    public static Ticket ticketEntity() {
        Ticket ticket = new Ticket();
            ticket.setBookedSeats("2");
        return ticket;
    }

    public static List<ReservationResponseDTO> reservationDTOList() {
        ReservationResponseDTO responseDTO = ReservationResponseDTO.builder()
                .userName("User")
                .status(Status.ACTIVE)
                .showTime(Date.valueOf("12-05-2024"))
                .movieName("TestName")
                .seat(2)
                .build();

        List<ReservationResponseDTO> responseDTOList = new ArrayList<>(List.of(responseDTO));
        return responseDTOList;
    }
}
