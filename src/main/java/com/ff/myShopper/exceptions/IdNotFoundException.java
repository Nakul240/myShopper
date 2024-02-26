package com.ff.myShopper.exceptions;

public class IdNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message = "Id not found ";

	public IdNotFoundException(String message) {
		this.message = message;
	}

	public IdNotFoundException() {

	}
	
	public String getMessage() {
		return message;
	}
}
