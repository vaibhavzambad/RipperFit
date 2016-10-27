package com.ripperfit.CustomExceptions;

public class UserAlreadyPresentException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserAlreadyPresentException(){
	}

	public UserAlreadyPresentException(String message){
		super(message);
	}

	public UserAlreadyPresentException(Throwable cause){
		super(cause);
	}

	public UserAlreadyPresentException(String message , Throwable cause){
		super(message,cause);
	}
}