package com.ripperfit.model;

import org.springframework.stereotype.Component;

/**
 * class to set and get the email address and password of the sender
 */
@Component
public class SenderVO {

	private String sender = "";
	private String password = "";

	/**
	 * method to get the email address of the sender
	 * @return : sender's email address
	 */
	public String getSender() {
		return sender;
	}
	
	/**
	 * method to set the email address of the sender
	 * @param receiver : sender's email address
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	/**
	 * method to get the email password of the sender
	 * @return : sender's email password
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * method to set the email password of the sender
	 * @param password : sender's email password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}