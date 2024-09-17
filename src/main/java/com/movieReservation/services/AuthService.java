package com.movieReservation.services;

import com.movieReservation.DTOs.requestsDTO.LogInRequest;
import com.movieReservation.DTOs.requestsDTO.UserRegisterRequest;
import com.movieReservation.exception.exceptions.UserAlreadyExistException;
import com.movieReservation.exception.exceptions.UserNotExistException;
import com.movieReservation.models.Roles;
import com.movieReservation.models.User;
import com.movieReservation.models.enums.RoleEnum;
import com.movieReservation.security.JwtUtils;
import com.movieReservation.services.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.NameNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;

    public void registerUser(UserRegisterRequest request) throws NameNotFoundException {

       Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()){
            throw new UserAlreadyExistException("User with email: "+request.getEmail()+" already Exist");
        }
        User user = new User();
            user.setName(request.getName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());

            Set<Roles> rolesHashSet = new HashSet<>();
            Roles role = new Roles();

            if(request.getRol() != null){
                request.getRol().forEach(r -> {
                            role.setRoles(RoleEnum.valueOf(r));
                            rolesHashSet.add(role);
                        }
                );
            }else{
                role.setRoles(RoleEnum.USER);
            }

        //user.setRol(rolesHashSet);
        user.setRol(rolesHashSet);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
        mailSenderService.sendEmail(request);
    }
    public String logIn(LogInRequest request) {

        Authentication auth = this.isAuthenticate(request.getEmail(),request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return jwtUtils.createToken(auth);
    }

    public Authentication isAuthenticate(String email, String password) {
        UserDetails userDetails = this.loadUserByUsername(email);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());
    }
    public UserDetails loadUserByUsername(String email) {

        User userEntity = userRepository.findByEmail(email)
                            .orElseThrow(() -> new UserNotExistException("The user with email: " + email + " does not exist."));

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRol().forEach(role ->
                        authorityList
                        .add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoles().name()))));

        userEntity.getRol().forEach(role ->
                authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoles()))
        );

        return new org.springframework.security.core.userdetails.User(userEntity.getName(), userEntity.getPassword(), userEntity.isEnabled(), userEntity.isAccountNoExpired(), userEntity.isCredentialNoExpired(), userEntity.isAccountNoLocked(), authorityList);
    }

}
