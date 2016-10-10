package com.ripperfit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

@Controller
public class HomeController {

	@RequestMapping(value="/", method=GET)
	public String home(HttpSession session) {
		
		/*if(session.getAttribute("email") != null) {
			return "Welcome";
		} else {*/
			return "index";
		/*}*/
	}
	
	@RequestMapping(value="signUp", method=GET)
	public String signUp() {
		return "signUpModal";
	}
<<<<<<< HEAD
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(WebRequest request) {
=======
	
	@RequestMapping(value="login", method=GET)
	public String login() {
>>>>>>> 2d0817804549089a1e76cbea5641bc972a9afc42
		return "signIn";
	}

	@RequestMapping(value="welcome", method=GET)
	public String welcome(HttpSession session) {

<<<<<<< HEAD
		if(session.getAttribute("email") != null) {
=======
		/*if(session.getAttribute("email") != null) {*/
>>>>>>> 2d0817804549089a1e76cbea5641bc972a9afc42
			return "Welcome";
		/*} else {
			return "signIn";
		}*/
	}

	@RequestMapping(value="success", method=GET)
	public String success() {
		return "success";
	}

	@RequestMapping(value="mailController", method=GET)
	public String mailController() {
<<<<<<< HEAD

		return "mailMessage";
	}
=======
		return "mailMessage";
	}
	
	@RequestMapping(value="admin", method=GET)
	public String admin() {
		return "AdminPage";
	}
	
	@RequestMapping(value="request", method=GET)
	public String request() {
		return "viewRequest";
	}
	
	@RequestMapping(value="role", method=GET)
	public String role() {
		return "ViewRole";
	}
>>>>>>> 2d0817804549089a1e76cbea5641bc972a9afc42
}