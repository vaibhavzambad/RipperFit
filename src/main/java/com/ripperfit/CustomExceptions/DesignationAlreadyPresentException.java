package com.ripperfit.CustomExceptions;

public class DesignationAlreadyPresentException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DesignationAlreadyPresentException(){
	}
	
	public DesignationAlreadyPresentException(String message){
		super(message);
	}

	public DesignationAlreadyPresentException(Throwable cause){
		super(cause);
	}

	public DesignationAlreadyPresentException(String message , Throwable cause){
		super(message,cause);
	}
}