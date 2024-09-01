package com.movieReservation.services.repository;


import com.movieReservation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByNameAndEmail(String userName, String email);

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String username);
}
