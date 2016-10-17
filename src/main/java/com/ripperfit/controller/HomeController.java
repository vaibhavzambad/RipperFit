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
		return "signUp";
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
	
	@RequestMapping(value="dashboard", method=GET)
	public String admin() {
		return "dashboard";
	}
	
	@RequestMapping(value="viewRequests", method=GET)
	public String viewRequests() {
		return "viewRequests";
	}

	@RequestMapping(value="viewResources", method=GET)
	public String viewResources() {
		return "viewResources";
	}
	@RequestMapping(value="viewRequestDetail", method=GET)
	public String viewRequestDetail() {
		return "viewRequestDetail";
	}
	
	@RequestMapping(value="addRequest", method=GET)
	public String addRequest() {
		return "addRequest";
	}
	
	@RequestMapping(value="viewDesignations", method=GET)
	public String viewDesignations() {
		return "ViewDesignations";
	}
	
	@RequestMapping(value="viewEmployees", method=GET)
	public String viewEmployees() {
		return "viewEmployees";
	}
	
	@RequestMapping(value="viewEmployeeRequests", method=GET)
	public String viewEmployeeRequests() {
		return "ViewEmployeeRequests";
	}
	
	@RequestMapping(value="addPosition")
	public String addPosition() {
		return "addPosition";
	}
}