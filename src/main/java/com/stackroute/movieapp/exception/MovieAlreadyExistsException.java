package com.stackroute.movieapp.exception;

public class MovieAlreadyExistsException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MovieAlreadyExistsException(String message) {
		super(message);
	}
}
