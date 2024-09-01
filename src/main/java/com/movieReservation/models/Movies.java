package com.movieReservation.models;
import lombok.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name= "Movies")
public class Movies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;

    @Column(length = 50)
    private String name;

    @Column(length = 250)
    private String description;

    @Column(length = 250)
    private String genre;

    private String posterImage;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShowTime> showTime = new ArrayList<>();

}
