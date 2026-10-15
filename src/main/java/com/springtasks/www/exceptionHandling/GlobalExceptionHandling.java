package com.springtasks.www.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<String> BookingNotFoundHandler(BookingNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}
    @ExceptionHandler(InvalidBookingOperationException.class)
    public ResponseEntity<String>InvalidBookingHandler(InvalidBookingOperationException ex){
    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
