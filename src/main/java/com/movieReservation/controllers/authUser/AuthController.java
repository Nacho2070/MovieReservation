package com.movieReservation.controllers.authUser;

import com.movieReservation.DTOs.requestsDTO.LogInRequest;
import com.movieReservation.DTOs.requestsDTO.UserRegisterRequest;
import com.movieReservation.services.AuthService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.NameNotFoundException;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> signUp(@RequestBody UserRegisterRequest request) throws NameNotFoundException {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/log-in")
    public ResponseEntity<String> login(@RequestBody LogInRequest request){
        return ResponseEntity.ok(authService.logIn(request));
    }

}
