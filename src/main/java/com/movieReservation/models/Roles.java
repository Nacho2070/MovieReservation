package com.movieReservation.models;

import com.movieReservation.models.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity(name= "Roles")
public class Roles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rolesId;
    @Column
    private RoleEnum roles;
    @ManyToMany(mappedBy = "rol")
    private Set<User> users = new HashSet<>();

}
