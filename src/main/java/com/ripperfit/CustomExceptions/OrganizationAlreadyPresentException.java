package com.ripperfit.CustomExceptions;

public class OrganizationAlreadyPresentException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrganizationAlreadyPresentException(){
	}

	public OrganizationAlreadyPresentException(String message){
		super(message);
	}

	public OrganizationAlreadyPresentException(Throwable cause){
		super(cause);
	}

	public OrganizationAlreadyPresentException(String message , Throwable cause){
		super(message,cause);
	}
}