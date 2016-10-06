package com.ripperfit.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ripperfit.oauth2.SecurityContext;

@Controller
@RequestMapping(value="/plus", method=GET)
public class PlusController {

	private Google google;
	
	@Autowired
	public PlusController(Google google) {
		System.out.println("in plus controller");
		this.google = google;
	}
}