package com.ripperfit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ripperfit.model.Employee;
import com.ripperfit.service.UserService;

/**
 * controller class to handle user data
 */

@Controller
public class UserController {

	private UserService userService;

	/**
	 * method to get UserServiceObject
	 * 
	 * @return : UserService object
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * method to set UserService object
	 * 
	 * @param UserService
	 */
	@Autowired(required = true)
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * method to get Employee By Email
	 * 
	 * 
	 * @return : Employee Object
	 */

	@RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
	public @ResponseBody Employee getUserByEmail(@RequestParam String email) {

		Employee emp = this.userService.getUserByEmail(email);
		System.out.println("mail");
		if (emp != null) {
			System.out.println("Employee" + emp.getEmployeeId());
		} else {
			System.out.println("employee not present");
		}

		return emp;
	}

	/**
	 * method to check that employee exists or not
	 */
	@RequestMapping(value = "/ifUserExists", method = RequestMethod.GET)
	public @ResponseBody void ifUserExists(@RequestParam String email) {
		Boolean flag = this.userService.ifUserExists(email);
		System.out.println(flag);
	}
	/**
	 * method to get Employee By EmployeeId 
	 * @return : Employee Object
	 */
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public @ResponseBody Employee getUserById(@RequestParam String ID) {
		int id = Integer.parseInt(ID);
		Employee emp = this.userService.getUserById(id);

		if (emp != null) {
			System.out.println("Employee" + emp.getEmployeeId());
		} else {
			System.out.println("employee not present");
		}

		return emp;
	}
	/**
	 * method to check that email and password are correct for login
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public @ResponseBody void login(@RequestParam String email,
			@RequestParam String password) {
		Employee emp = this.userService.login(email, password);
		System.out.println(emp.getEmail());
	}
	/**
	 * method to register employee
	 * 
	 */
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody void insert(@RequestBody Employee emp,
			HttpServletRequest request) {

		System.out.println("id is" + emp.getEmployeeId());
		System.out.println("date: " + emp.getDateOfBirth());
		System.out.println("employee : " + emp);

		Boolean flag = this.userService.registerUser(emp);

	}
	/**
	 * method to Update Employee
	 * 
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody void update(@RequestBody Employee emp,
			HttpServletRequest request) {

		System.out.println("id is" + emp.getEmployeeId());
		System.out.println("date: " + emp.getDateOfBirth());
		System.out.println("employee : " + emp);

		this.userService.updateUser(emp);

	}
}
