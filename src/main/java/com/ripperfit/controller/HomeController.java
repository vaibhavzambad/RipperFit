package com.ripperfit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * class to redirect to the index page
 */
@Controller
public class HomeController {
	
	/**
	 * method to redirect the project to the index page
	 * @return : String(index page name)
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "index.html";
	}
}