package com.movieReservation.controllers.auth;

import com.movieReservation.DTOs.requestsDTO.LogInRequest;
import com.movieReservation.DTOs.requestsDTO.UserRegisterRequest;
import com.movieReservation.services.AuthService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody UserRegisterRequest request) throws NameNotFoundException {
        authService.registerUser(request);
    }

    @PostMapping("/log-in")
    public ResponseEntity<String> login(@RequestBody LogInRequest request){
        return ResponseEntity.ok(authService.logIn(request));
    }

}
