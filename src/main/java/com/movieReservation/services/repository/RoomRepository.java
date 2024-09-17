package com.movieReservation.services.repository;

import com.movieReservation.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
   Room findByAddress(String address);

   Room findByRoomName(String roomName);
}
