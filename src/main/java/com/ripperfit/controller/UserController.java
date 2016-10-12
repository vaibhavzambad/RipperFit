package com.ripperfit.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.model.Employee;
import com.ripperfit.model.Login;
import com.ripperfit.service.UserService;

/**
 * controller class to handle user data
 */
@RequestMapping(value = "/employee")
@RestController
public class UserController {

	private UserService userService;

	/**
	 * method to get UserServiceObject
	 * @return : UserService object
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * method to set UserService object
	 * @param UserService
	 */
	@Autowired(required = true)
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * method to get employee with his/her email address
	 * @param email : employee's email address
	 * @return : ResponseEntity with no object
	 */
	@RequestMapping(value = "/getEmployeeByEmail", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam String email) {

		Employee employee = this.userService.getEmployeeByEmail(email);
		if (employee != null) {
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * method to get Employee By EmployeeId
	 * @param id : employee id
	 * @return : ResponseEntity with Employee object
	 */
	@RequestMapping(value = "/getEmployeeById", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@RequestParam String Id) {
		
		int id = Integer.parseInt(Id);
		Employee employee = this.userService.getEmployeeById(id);
		if (employee != null) {
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * method to check that email and password are correct for login
	 * @param login : Login object with email and password
	 * @return : ResponseEntity with no object
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Void> login(@RequestBody Login login, HttpServletRequest request) {	
		
		HttpSession session = request.getSession();
		Employee employee = this.userService.login(login.getEmail(), login.getPassword());
		
		if (employee != null) {
			session.setAttribute("email", login.getEmail());
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 * method to register employee
	 * @param employee : employee object
	 * @return : ResponseEntity with no object
	 */
	@RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
	public ResponseEntity<Void> addEmployee(@RequestBody Employee employee) {
		
		int result = this.userService.registerEmployee(employee);
		if(result == 1) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else if(result == 2) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
	
	/**
	 * method to Update Employee
	 * @param employee : employee object
	 * @return : ResponseEntity with no object
	 */
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Employee employee) {
		
		this.userService.updateUser(employee);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * 
	 */
	@RequestMapping(value="/logout",method= RequestMethod.POST)
	public ResponseEntity<Void> logout(HttpSession session){
		
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}