package com.movieReservation.services;

import com.movieReservation.DTOs.requestsDTO.LogInRequest;
import com.movieReservation.DTOs.requestsDTO.UserRegisterRequest;
import com.movieReservation.models.Roles;
import com.movieReservation.models.User;
import com.movieReservation.models.enums.RoleEnum;
import com.movieReservation.security.JwtUtils;
import com.movieReservation.services.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import javax.naming.Context;
import javax.naming.NameNotFoundException;
import java.util.*;

@Service
@AllArgsConstructor
public class AuthService implements UserDetailsService {

    private UserRepository userRepository;
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public String registerUser(UserRegisterRequest request) throws NameNotFoundException {

       Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()){
            throw new NameNotFoundException("User with "+request.getEmail()+"Already exist");
        }
        User user = new User();
            user.setName(request.getName());
            user.setLastName(request.getLastName());
            user.setEmail(request.getEmail());

            HashSet<Roles> rolesHashSet = new HashSet<>();
            Roles role = new Roles();

            if(request.getRol() != null){
                request.getRol().forEach(r -> {
                    /*
                    if (!validateRole(r)){
                                throw new RuntimeException("Wrong role! must be: ADMIN, USER or DEVELOPER");
                            }
                      */
                            role.setRoles(RoleEnum.valueOf(r));
                            rolesHashSet.add(role);
                        }
                );
            }else{
                role.setRoles(RoleEnum.USER);
            }
        //user.setRol(rolesHashSet);
        System.out.println("aca");
        user.setRol(rolesHashSet);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return "User Added";
    }

    private boolean validateRole(String r) {
        switch (r.toUpperCase()) {
            case "DEVELOPER":
            case "ADMIN":
            case "USER":
                return true;
            default:
                return false;
        }
    }


    public String logIn(LogInRequest request) {

        Authentication auth = this.isAuthenticate(request.getUserName(),request.getPassword());
        SecurityContextHolder.getContext().setAuthentication(auth);

        return jwtUtils.createToken(auth);
    }

    public Authentication isAuthenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException(String.format("Invalid username or password"));
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
    public UserDetails loadUserByUsername(String username) {

        User userEntity = userRepository.findByName(username)
                            .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

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
