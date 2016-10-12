package com.ripperfit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
	  
/**
 * class to send email to the users
 */
@RestController
@RequestMapping(value = "/mail")
public class MailUtility {
	
    private JavaMailSender mailSender;
	
    /**
     * 
     * @return
     */
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	/**
	 * 
	 * @param mailSender
	 */
	@Autowired(required=true)
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "registrationMail")
	public ResponseEntity<Void> registrationMail(@RequestBody String email) {
		
		System.out.println("email: "+email);
		String subject = "Register your email account to RipperFit";
		String body = "Hello "+email+", You are successfully registered";
		sendMail(subject, body, email);
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	/**
	 * method to send the mail
	 * @param from : SenderVO object for sender's details
	 * @param to : Internet address of recipients who will be in TO block
	 * @param cc : Internet address of recipients who will be in CC block
	 * @param bcc : Internet address of recipients who will be in BCC block
	 * @return : String message
	 */
	public void sendMail(String subject, String body, String receiver) {
		
		SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(receiver);
        email.setSubject(subject);
        email.setText(body);
         
        // sends the e-mail
        mailSender.send(email);
	}
}