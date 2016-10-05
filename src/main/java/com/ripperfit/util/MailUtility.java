package com.ripperfit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ripperfit.model.ReceiverVO;
import com.ripperfit.model.SenderVO;
	  
/**
 * class to send email to the users
 */
public class MailUtility {
	
	/**
	 * method to take sender and recipients email addresses to map them in Internet addresses and call sendMail method 
	 * @param sender : SenderVO object
	 * @param receivers : Variable number of ReceiverVO objects
	 * @throws MessagingException 
	 */
	public static String mail(String subject, String body, SenderVO sender, ReceiverVO... receivers) throws MessagingException {
		
		List<ReceiverVO> receiverList = new ArrayList<ReceiverVO>();
		for(int index = 0 ; index < 3 ; index++) {
			receiverList.add(new ReceiverVO());
		}
		
		int index = 0;
		for(ReceiverVO reciever : receivers) {
			if(index < 3) {
				receiverList.set(index, reciever);
				index++;
			}
		}
		
		//Getting the recipients list
		List<String> toList = receiverList.get(0).getReceiver();
		List<String> bccList = receiverList.get(1).getReceiver();
		List<String> ccList = receiverList.get(2).getReceiver();

		//check if there is atleast one recipient in TO block
		if(toList.size() == 0) {
			
			return "Please specify atleast one receiver in TO block.";
		}
		
		//converting in email addresses
		InternetAddress[] toAddress = createInternetAddress(toList);
		InternetAddress[] bccAddress = createInternetAddress(bccList);
		InternetAddress[] ccAddress = createInternetAddress(ccList);
		
		return sendMail(subject, body, sender, toAddress, bccAddress, ccAddress);
	}
	
	/**
	 * method to convert email addresses from String addresses 
	 * @param address : List of email addresses
	 * @throws MessagingException
	 * @return : List of email addresses
	 */
	public static InternetAddress[] createInternetAddress(List<String> address) throws MessagingException {
		
		int counter = 0;
		InternetAddress[] internetAddress = null;
		
		//converting the String addresses into Internet addresses if there is atleast one recipient
		if(address.size() > 0) {
			
			//giving memory to InternetAddress object
			internetAddress = new InternetAddress[address.size()];
			//creating Internet Addresses
			for (String recipient : address) {
				
				internetAddress[counter] = new InternetAddress(recipient.trim());
				counter++;
			}
		}
		return internetAddress;
	}
	
	/**
	 * method to send the mail
	 * @param from : SenderVO object for sender's details
	 * @param to : Internet address of recipients who will be in TO block
	 * @param cc : Internet address of recipients who will be in CC block
	 * @param bcc : Internet address of recipients who will be in BCC block
	 * @return : String message
	 */
	@RequestMapping(value = "/addRequest", method = RequestMethod.PUT)
	public static String sendMail(String subject, String body, SenderVO from, InternetAddress[] toAddress, InternetAddress[] bccAddress, InternetAddress[] ccAddress) {
		
		//sender's email address
		final String senderAddress = from.getSender();
		//sender's email password
		final String senderPassword = from.getPassword();
		
		//setting the mail properties
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.debug", "true");
		properties.put("mail.smtp.auth", "true");
		
		//properties.put("mail.smtp.port", "587");
		//TLS (Transport Layer Security) mechanism
		properties.put("mail.smtp.starttls.enable", "true");
		
	/*
		//SSL (Secure Socket Layer) mechanism
		properties.put("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	*/
		
		//Get the session object
		Session session = Session.getDefaultInstance(properties, new Authenticator() {
			//authenticating the sender
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderAddress, senderPassword);
			}
		});
	  
		try {
					
			//Composing the message
			MimeMessage message = new MimeMessage(session);									//creating MimeMessage object
			message.setFrom(new InternetAddress(senderAddress));							//setting sender's information
			message.setRecipients(Message.RecipientType.TO, toAddress);						//setting recipient's in TO block
			message.setRecipients(Message.RecipientType.BCC, bccAddress);					//setting recipient's in BCC block
			message.setRecipients(Message.RecipientType.CC, ccAddress);						//setting recipient's in CC block
			message.setSubject(subject);													//setting message's subject
			message.setContent(body, "text/html");											//setting message's content
			
	  
			//Send message
			Transport.send(message);
			
			return "message sent successfully....";
	  
		} catch (MessagingException mex) {
			
			mex.printStackTrace();
			return "Message sending failed...";
		}
	}
}