package com.movieReservation.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name= "Room")
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomName;
    private Long capacity;

    @OneToMany(mappedBy = "room")
    private List<Seats> seats = new ArrayList<>();

    @OneToMany(mappedBy = "room")
    private List<ShowTime> showTimes  = new ArrayList<>();

}
