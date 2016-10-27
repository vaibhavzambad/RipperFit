package com.ripperfit.CustomExceptions;

public class UserNotExistsException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UserNotExistsException(){
	}

	public UserNotExistsException(String message){
		super(message);
	}

	public UserNotExistsException(Throwable cause){
		super(cause);
	}

	public UserNotExistsException(String message , Throwable cause){
		super(message,cause);
	}

}
