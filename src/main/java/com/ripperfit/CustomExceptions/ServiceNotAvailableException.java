package com.ripperfit.CustomExceptions;

public class ServiceNotAvailableException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ServiceNotAvailableException(){
	}

	public ServiceNotAvailableException(String message){
		super(message);
	}

	public ServiceNotAvailableException(Throwable cause){
		super(cause);
	}

	public ServiceNotAvailableException(String message , Throwable cause){
		super(message,cause);
	}

}
