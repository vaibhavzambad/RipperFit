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
	public String home() {
		return "index";
	}
	
	@RequestMapping(value="signUp", method=GET)
	public String signUp() {
		return "signUpModal";
	}
	@RequestMapping(value="login", method=RequestMethod.GET)
	public String login(WebRequest request) {
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