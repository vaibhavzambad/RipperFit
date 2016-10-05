package com.ripperfit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ripperfit.oauth2.Employee1;
import com.ripperfit.oauth2.SecurityContext;

@Controller
public class HomeController {

	@RequestMapping(value="/", method=GET)
	public String home() {
		String page="";
		if(SecurityContext.employeeSignedIn()){
			page="home";
		}
		else{
			page="signin";
		}
		return page;
	}

}