package com.ripperfit.CustomExceptions;

public class DepartmentAlreadyPresentException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DepartmentAlreadyPresentException(){
	}
	
	public DepartmentAlreadyPresentException(String message){	
		super(message);
	}
	
	public DepartmentAlreadyPresentException(Throwable cause){
		super(cause);
	}
	
	public DepartmentAlreadyPresentException(String message , Throwable cause){
		super(message,cause);
	}
}