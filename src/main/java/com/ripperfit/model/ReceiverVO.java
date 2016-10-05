package com.ripperfit.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * class to set and get the email addresses of the recipients
 */
@Component
public class ReceiverVO {

	private List<String> receiver = new ArrayList<String>();

	/**
	 * method to get the email addresses of the recipients
	 * @return : list of recipient's email addresses
	 */
	public List<String> getReceiver() {
		return receiver;
	}
	
	/**
	 * method to add a recipient's email address in the list
	 * @param receiver : recipient's email address
	 */
	public void setReceiver(String receiver) {
		this.receiver.add(receiver);
	}

	/**
	 * method to set the email addresses of the recipients
	 * @param receiver : list of recipient's email addresses
	 */
	public void setReceiver(List<String> receiver) {
		this.receiver = receiver;
	}
}