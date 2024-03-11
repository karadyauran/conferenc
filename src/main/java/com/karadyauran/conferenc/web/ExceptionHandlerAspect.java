package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.error.BookingWasNotFoundException;
import com.karadyauran.conferenc.error.EmailIsAlreadyTakenException;
import com.karadyauran.conferenc.error.EventCategoryWasNotFoundException;
import com.karadyauran.conferenc.error.EventWasNotFoundException;
import com.karadyauran.conferenc.error.SessionWasNotFoundException;
import com.karadyauran.conferenc.error.UserIdWasNotFoundException;
import com.karadyauran.conferenc.error.UsernameIsAlreadyExistsException;
import com.karadyauran.conferenc.error.UsernameWasNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandlerAspect
{
    @ExceptionHandler(UserIdWasNotFoundException.class)
    public ResponseEntity<?> handleUserWasNotFoundException(UserIdWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UsernameIsAlreadyExistsException.class)
    public ResponseEntity<?> handleUsernameIsAlreadyExists(UsernameIsAlreadyExistsException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UsernameWasNotFoundException.class)
    public ResponseEntity<String> handleUsernameWasNotFound(UsernameWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EmailIsAlreadyTakenException.class)
    public ResponseEntity<?> handleEmailIsAlreadyTaken(EmailIsAlreadyTakenException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(BookingWasNotFoundException.class)
    public ResponseEntity<?> handleBookingWasNotFound(BookingWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EventCategoryWasNotFoundException.class)
    public ResponseEntity<?> handleEventCategoryWasNotFound(EventCategoryWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EventWasNotFoundException.class)
    public ResponseEntity<?> handleEventWasNotFound(EventWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(SessionWasNotFoundException.class)
    public ResponseEntity<?> handleSessionWasNotFound(SessionWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllExceptions(Exception ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }
}
