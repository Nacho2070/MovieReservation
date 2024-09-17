package com.movieReservation.services;

import com.movieReservation.DTOs.requestsDTO.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    @Autowired
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void sendEmail(UserRegisterRequest userRequest){

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(userRequest.getEmail());
        msg.setText(
                "Dear " + userRequest.getName()
                        + userRequest.getLastName()
                        + "your account was created successfully "
                        + userRequest.getRol());

        try {
            this.mailSender.send(msg);
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
