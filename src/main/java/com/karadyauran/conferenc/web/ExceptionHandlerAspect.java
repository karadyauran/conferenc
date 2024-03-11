package com.karadyauran.conferenc.web;

import com.karadyauran.conferenc.error.BookingWasNotFoundException;
import com.karadyauran.conferenc.error.EmailIsAlreadyTakenException;
import com.karadyauran.conferenc.error.EventCategoryWasNotFoundException;
import com.karadyauran.conferenc.error.EventWasNotFoundException;
import com.karadyauran.conferenc.error.SessionWasNotFoundException;
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
    @ExceptionHandler(UsernameWasNotFoundException.class)
    public ResponseEntity<String> handleUserWasNotFoundException(UsernameWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UsernameIsAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameIsAlreadyExists(UsernameIsAlreadyExistsException ex)
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
    public ResponseEntity<String> handleEmailIsAlreadyTaken(EmailIsAlreadyTakenException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(BookingWasNotFoundException.class)
    public ResponseEntity<String> handleBookingWasNotFound(BookingWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EventCategoryWasNotFoundException.class)
    public ResponseEntity<String> handleEventCategoryWasNotFound(EventCategoryWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(EventWasNotFoundException.class)
    public ResponseEntity<String> handleEventWasNotFound(EventWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(SessionWasNotFoundException.class)
    public ResponseEntity<String> handleSessionWasNotFound(SessionWasNotFoundException ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body(ex.getMessage());
    }
}
