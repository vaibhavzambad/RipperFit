package com.ripperfit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
	
	@RequestMapping(value="signUpAfterSocialLogin", method=GET)
	public String signUpAfterSocialLogin() {
		return "SignUpAfterSocialLogin";
	}
	
	@RequestMapping(value="login", method=GET)
	public String login() {
		return "signIn";
	}

	@RequestMapping(value="mailController", method=GET)
	public String mailController() {
		return "mailMessage";
	}
	
	@RequestMapping(value="admin", method=GET)
	public String admin() {
		return "AdminPage";
	}
	
	@RequestMapping(value="helpdesk", method=GET)
	public String helpdesk() {
		return "HelpDesk";
	}
	
	@RequestMapping(value="employee", method=GET)
	public String employee() {
		return "UserDB";
	}
	
	@RequestMapping(value="request", method=GET)
	public String request() {
		return "viewRequest";
	}
	
	@RequestMapping(value="role", method=GET)
	public String role() {
		return "ViewRole";
	}
	
	@RequestMapping(value="viewEmployee", method=GET)
	public String viewEmployee() {
		return "viewEmployee";
	}
	
	@RequestMapping(value="requestemployee", method=GET)
	public String requestemployee() {
		return "ViewEmployeeRequest";
	}
	
	@RequestMapping(value="DBHome")
	public String HomedashBoard() {
		return "DBHome";
	}
}