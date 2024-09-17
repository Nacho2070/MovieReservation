package com.movieReservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.movieReservation.services.repository")
public class MovieReservationApplication {
	public static void main(String[] args) {
		SpringApplication.run(MovieReservationApplication.class, args);
	}
}
