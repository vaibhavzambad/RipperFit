package com.ripperfit.CustomExceptions;

public class ResourceNotExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ResourceNotExistsException(){
	}

	public ResourceNotExistsException(String message){
		super(message);
	}

	public ResourceNotExistsException(Throwable cause){
		super(cause);
	}

	public ResourceNotExistsException(String message , Throwable cause){
		super(message,cause);
	}
	
}
