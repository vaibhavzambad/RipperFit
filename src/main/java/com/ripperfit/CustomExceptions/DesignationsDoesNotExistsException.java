package com.ripperfit.CustomExceptions;

public class DesignationsDoesNotExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DesignationsDoesNotExistsException(){
	}

	public DesignationsDoesNotExistsException(String message){
		super(message);
	}

	public DesignationsDoesNotExistsException(Throwable cause){
		super(cause);
	}

	public DesignationsDoesNotExistsException(String message , Throwable cause){
		super(message,cause);
	}

}
