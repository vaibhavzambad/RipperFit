package com.ripperfit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="signUp", method=GET)
	public String signUp() {
		return "signUpModal";
	}
	
	@RequestMapping(value="login", method=GET)
	public String login() {
		return "signIn";
	}

	@RequestMapping(value="welcome", method=GET)
	public String welcome(HttpSession session) {

		return "Welcome";
	}

	@RequestMapping(value="success", method=GET)
	public String success() {
		return "success";
	}

	@RequestMapping(value="mailController", method=GET)
	public String mailController() {
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
}