package com.movieReservation.exception;

import com.movieReservation.exception.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<?> userNotFoundException(UserNotFoundException exception, WebRequest webRequest){
        return new ResponseEntity<>(new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({UserAlreadyExistException.class})
    public ResponseEntity<?> userAlreadyExist(UserAlreadyExistException exception, WebRequest webRequest){
        return new ResponseEntity<>(new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> notFoundException(NotFoundException exception, WebRequest webRequest){
        return new ResponseEntity<>(new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({NotResultsException.class})
    public ResponseEntity<?> notResultsExceptions(NotResultsException exception, WebRequest webRequest){
        return new ResponseEntity<>(new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler({UserNotExistException.class})
    public ResponseEntity<?> userNotExistException(UserNotExistException exception, WebRequest webRequest){
        return new ResponseEntity<>(new ErrorDetail(new Date(), exception.getMessage(), webRequest.getDescription(false)), HttpStatus.NOT_FOUND);
    }
}

