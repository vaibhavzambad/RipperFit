package com.ripperfit.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.model.Login;

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
	public ResponseEntity<Void> registrationMail(@RequestBody Login login) {

		String subject = "Welcome to ripperFit";
		String body = "Hello "+login.getEmail()+", You are successfully registered\nYour email-id : "+login.getEmail()+
				"\nYour password is : "+login.getPassword();
		sendMail(subject, body, login.getEmail());
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "registrationMailOnSignup")
	public ResponseEntity<Void> registrationMailOnSignUp(@RequestBody String email) {

		String subject = "Welcome to ripperFit";
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