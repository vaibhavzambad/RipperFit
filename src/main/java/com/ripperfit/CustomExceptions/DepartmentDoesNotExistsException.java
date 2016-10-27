package com.ripperfit.CustomExceptions;

public class DepartmentDoesNotExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DepartmentDoesNotExistsException(){
	}
	
	public DepartmentDoesNotExistsException(String message){	
		super(message);
	}
	
	public DepartmentDoesNotExistsException(Throwable cause){
		super(cause);
	}
	
	public DepartmentDoesNotExistsException(String message , Throwable cause){
		super(message,cause);
	}
	
}
