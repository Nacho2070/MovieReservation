package org.mock.services;

import com.movieReservation.DTOs.requestsDTO.LogInRequest;
import com.movieReservation.DTOs.requestsDTO.UserRegisterRequest;
import com.movieReservation.models.User;
import com.movieReservation.services.AuthService;
import com.movieReservation.services.repository.UserRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mock.DataProvider;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.naming.NameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthService authService;
    @Test
    void registerUser() throws NameNotFoundException {

        User user = DataProvider.newUser();
        UserRegisterRequest registerRequest = DataProvider.newUserRegisterDTO();

        when(userRepository.findByEmail( registerRequest.getEmail() )).thenReturn( Optional.of(user) );
        when(userRepository.save(Mockito.any( User.class ))).thenReturn( user );

        authService.registerUser(registerRequest);

        Mockito.verify(userRepository, times(2)).save(user);

    }

    @Test
    void logIn() {
        User user = DataProvider.newUser();
        LogInRequest logInRequest = DataProvider.newUserLogInDTO();

        when(userRepository.findByEmail(logInRequest.getEmail())).thenReturn(Optional.of(user));

        String jwtValue = authService.logIn(logInRequest);

        Assertions.assertNotNull(jwtValue);
    }
    @Test
    @DisplayName("JUnit test for login with wrong password operation")
    void WhenUserTryToLogInWithWrongPassword_thenReturnAnException(){
        User user = DataProvider.newUser();
        user.setPassword("wrongPasswordTest");

        LogInRequest logInRequest = DataProvider.newUserLogInDTO();

        when(userRepository.findByEmail(logInRequest.getEmail())).thenReturn(Optional.of(user));

        Assertions.assertThrows(BadCredentialsException.class, ()->{
            authService.logIn(logInRequest);
        });
    }
}