package com.movieReservation.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "ShowTime")
@AllArgsConstructor
public class ShowTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showTimeId;

    private Date showDate;
    private Time StartTime;

    @ManyToOne
    @JoinColumn(name = "movies_id")
    private Movies movie;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<ShowSeats> showSeats = new ArrayList<>();

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    public void setTicket(Ticket ticket){
        this.tickets.add(ticket);
    }

}
