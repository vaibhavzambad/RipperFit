package com.ripperfit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpSession;

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
		
		return "signIn";
	}
	
	@RequestMapping(value="welcome", method=GET)
	public String welcome(HttpSession session) {
		
		if(session.getAttribute("email") != null) {
			return "Welcome";
		} else {
			return "signIn";
		}
	}
	
	@RequestMapping(value="success", method=GET)
	public String success() {
		return "success";
	}
	
	@RequestMapping(value="mailController", method=GET)
	public String mailController() {
		
		return "mailMessage";
	}
}