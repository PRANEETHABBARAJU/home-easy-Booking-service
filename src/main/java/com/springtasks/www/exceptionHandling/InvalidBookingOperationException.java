package com.springtasks.www.exceptionHandling;

public class InvalidBookingOperationException extends RuntimeException {
	public InvalidBookingOperationException(String message) {
		super(message);
	}

}
