package com.movieReservation.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity(name="RoomSeats")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatsId;
    private Boolean isAvailable;
    private String seatNo;

    @ManyToOne
    private Room room;

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
