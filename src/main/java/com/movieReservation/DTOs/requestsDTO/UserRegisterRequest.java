package com.movieReservation.DTOs.requestsDTO;

import com.movieReservation.models.enums.RoleEnum;
import lombok.Getter;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Getter
public class UserRegisterRequest {
    private String name;
    private String lastName;
    private String email;
    private List<String> rol;
    private String password;
}
