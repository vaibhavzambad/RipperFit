package com.ripperfit.CustomExceptions;

public class CommentDoesNotExistsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CommentDoesNotExistsException(){
	}
	
	public CommentDoesNotExistsException(String message){	
		super(message);
	}
	
	public CommentDoesNotExistsException(Throwable cause){
		super(cause);
	}
	
	public CommentDoesNotExistsException(String message , Throwable cause){
		super(message,cause);
	}
}
