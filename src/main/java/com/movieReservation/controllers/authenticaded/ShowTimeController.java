package com.movieReservation.controllers.authenticaded;

import com.movieReservation.DTOs.requestsDTO.ShowTimeRequestDTO;
import com.movieReservation.services.ShowTimeService;
import lombok.AllArgsConstructor;
import org.hibernate.internal.CoreLogging;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/showTime")
public class ShowTimeController {

    private final ShowTimeService showTimeService;

    @PostMapping("/addShowTime")
    public ResponseEntity<String> addShowTime(@RequestBody ShowTimeRequestDTO showTimeRequestDTO) {

        return ResponseEntity.ok(showTimeService.addShow(showTimeRequestDTO));
    }

}
