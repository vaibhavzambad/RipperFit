package com.ripperfit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ripperfit.oauth2.Employee1;
import com.ripperfit.oauth2.SecurityContext;

@Controller
public class HomeController {

	@RequestMapping(value="/", method=GET)
	public String home() {
		return "index";
	}
}