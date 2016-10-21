package com.ripperfit.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.model.Employee;
import com.ripperfit.model.Login;
import com.ripperfit.service.UserService;

/**
 * class to send email to the users
 */
@RestController
@RequestMapping(value = "/mail")
public class MailUtility {

	private JavaMailSender mailSender;
	private UserService userService;

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
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	@Autowired(required=true)
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "registrationMail", method = RequestMethod.POST)
	public ResponseEntity<Void> registrationMail(@RequestBody Login login) {

		String subject = "Welcome to ripperFit";
		String body = emailTemplate(login.getEmail());
		sendMail(subject, body, login.getEmail());
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = "registrationMailOnSignUp", method = RequestMethod.POST)
	public ResponseEntity<Void> registrationMailOnSignUp(@RequestBody String email) {

		String subject = "Welcome to ripperFit";
		String body = emailTemplate(email);
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

		MimeMessage message = mailSender.createMimeMessage(); 
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(receiver);  
			helper.setSubject(subject);
			helper.setText(body, true);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		// sends the e-mail
		mailSender.send(message);
	}

	public String emailTemplate(String email) {

		Employee employee = userService.getEmployeeByEmail(email);
		String gender = "";
		if(employee.getGender().equalsIgnoreCase("male")) {
			gender = "Mr.";
		} else {
			gender = "Miss";
		}
		String body = "<div><h1>Welcome To The RipperFit!!!</h1><p><b>Hi "+gender+" "+employee.getFirstName()+" "+employee.getLastName()+"</b>, welcome to  RipperFit Inc. Great to have you on board.</p>"
				+ "</div><hr /><br /><br /><div>Email : <b>"+email+"</b><br />Password: <b>"+employee.getPassword()+"</b></div><br /><br /><hr />"
				+ "<div><p><h4>Great to have you on borad "+gender+" "+employee.getFirstName()+" "+employee.getLastName()+"</h4></p><p>RipperFit Inc, <br />"
				+ "Metacube Software Pvt. Ltd., <br />Jaipur, Rajasthan-302022</p><p><b>Powered By <span>VARAAS<span><b></p></div>";

		return body;
	}
}