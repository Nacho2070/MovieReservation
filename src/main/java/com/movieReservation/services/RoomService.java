package com.movieReservation.services;

import com.movieReservation.DTOs.requestsDTO.CinemaRoomRequestDTO;
import com.movieReservation.DTOs.requestsDTO.RoomSeatsRequestDTO;
import com.movieReservation.exception.exceptions.NotFoundException;
import com.movieReservation.models.Room;
import com.movieReservation.models.Seats;
import com.movieReservation.services.repository.RoomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    public String addCinemaRoom(CinemaRoomRequestDTO cinemaRoomRequestDTO){

        if (roomRepository.findByAddress(cinemaRoomRequestDTO.getAddress()) != null) {
            throw new RuntimeException("Already exist");
        }

        Room room = new Room();
            room.setRoomName(cinemaRoomRequestDTO.getRoomName());
            room.setAddress(cinemaRoomRequestDTO.getAddress());

        roomRepository.save(room);

        return "Room added successfully";
    }

    public String addSeatsToCinemaRoom(RoomSeatsRequestDTO roomSeatsRequestDTO){

        if (roomSeatsRequestDTO == null || roomSeatsRequestDTO.getRoomName() == null) {
            throw new IllegalArgumentException("RoomSeatsRequestDTO or RoomName cannot be null");
        }

        Room room = roomRepository.findByRoomName(roomSeatsRequestDTO.getRoomName());
        if(room == null){
            throw new NotFoundException("Room does not exist");
        }
        Set<Seats> seatsHash = new HashSet<>();
        int seatNum = 0;

        for(int i = 1; i <= roomSeatsRequestDTO.getTotalSeats(); i++ ){
            seatNum++;

            Seats seat = new Seats();
            seat.setSeatNo(Integer.toString(seatNum));
            seatsHash.add(seat);
        }
        room.setSeats(seatsHash);
        // Update the room with news seats
        roomRepository.save(room);

        return "Seats on "+ roomSeatsRequestDTO.getRoomName()+" added successfully";
    }
}
