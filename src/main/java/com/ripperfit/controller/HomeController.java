package com.ripperfit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;

=======
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value="/", method=GET)
	public String home() {
		return "index";
	}
	@RequestMapping(value="signUp", method=GET)
	public String signUp() {
		return "signUpModal";
	}
	@RequestMapping(value="login", method=GET)
	public String login() {
<<<<<<< HEAD
		
=======
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
		return "signIn";
	}
	
	@RequestMapping(value="welcome", method=GET)
<<<<<<< HEAD
	public String welcome(HttpSession session) {
		
		if(session.getAttribute("email") != null) {
			return "Welcome";
		} else {
			return "signIn";
		}
=======
	public String welcome() {
		return "Welcome";
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
	}
	
	@RequestMapping(value="success", method=GET)
	public String success() {
		return "success";
	}
}