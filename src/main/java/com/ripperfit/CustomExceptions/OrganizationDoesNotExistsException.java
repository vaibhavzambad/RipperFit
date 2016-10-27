package com.ripperfit.CustomExceptions;

public class OrganizationDoesNotExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public OrganizationDoesNotExistsException(){
	}

	public OrganizationDoesNotExistsException(String message){
		super(message);
	}

	public OrganizationDoesNotExistsException(Throwable cause){
		super(cause);
	}

	public OrganizationDoesNotExistsException(String message , Throwable cause){
		super(message,cause);
	}

}
