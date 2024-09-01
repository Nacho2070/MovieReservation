package com.movieReservation.models;

import com.movieReservation.models.enums.Status;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketId;

    private String bookedSeats;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "showTime_id")
    private ShowTime showTime;

    @Enumerated(EnumType.STRING)
    private Status status;

}
