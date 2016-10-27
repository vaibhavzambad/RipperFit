package com.ripperfit.CustomExceptions;

public class ResourceAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ResourceAlreadyExistsException(){
	}

	public ResourceAlreadyExistsException(String message){
		super(message);
	}

	public ResourceAlreadyExistsException(Throwable cause){
		super(cause);
	}

	public ResourceAlreadyExistsException(String message , Throwable cause){
		super(message,cause);
	}
}
