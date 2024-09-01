package com.movieReservation.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name= "ShowSeats")
public class ShowSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ShowSeatsId;

    private String seatNo;

    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn
    private ShowTime show;
}
