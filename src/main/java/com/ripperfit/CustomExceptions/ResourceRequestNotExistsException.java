package com.ripperfit.CustomExceptions;

public class ResourceRequestNotExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ResourceRequestNotExistsException(){
	}

	public ResourceRequestNotExistsException(String message){
		super(message);
	}

	public ResourceRequestNotExistsException(Throwable cause){
		super(cause);
	}

	public ResourceRequestNotExistsException(String message , Throwable cause){
		super(message,cause);
	}
}
