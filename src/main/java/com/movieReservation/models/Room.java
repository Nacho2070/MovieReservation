package com.movieReservation.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@Entity(name= "Room")
@AllArgsConstructor
@NoArgsConstructor
public class Room  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;
    private String roomName;
    private String address;

    @OneToMany(mappedBy = "room")
    private Set<Seats> seats = new HashSet<>();

    @OneToMany(mappedBy = "room")
    private List<ShowTime> showTimes  = new ArrayList<>();

}
