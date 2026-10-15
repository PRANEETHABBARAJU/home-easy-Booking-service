package com.springtasks.www.exceptionHandling;

public class BookingNotFoundException extends RuntimeException {
	public BookingNotFoundException(String message) {
		super(message);
	}

}
