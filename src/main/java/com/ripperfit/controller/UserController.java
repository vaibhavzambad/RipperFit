package com.ripperfit.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public ResponseEntity<Employee> getUserByEmail(@RequestParam String email) {

		Employee employee = this.userService.getUserByEmail(email);
		System.out.println("mail");
		if (employee != null) {
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
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
	public ResponseEntity<Employee> getUserById(@RequestParam String Id) {
		int id = Integer.parseInt(Id);
		Employee employee = this.userService.getUserById(id);
		
		if (employee != null) {
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * method to check that email and password are correct for login
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Void> login(@RequestBody Login login) {
		System.out.println("email: "+login.getEmail());
		System.out.println("password: "+login.getPassword());
		Employee employee = this.userService.login(login.getEmail(), login.getPassword());
		if (employee != null) {
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
	/**
	 * method to register employee
	 * 
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Employee employee) {
		
		System.out.println("dfd"+employee.getAddress());
		int result = this.userService.registerUser(employee);
		if(result == 1){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}else if(result == 2){
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}
	/**
	 * method to Update Employee
	 * 
	 */
	@Consumes(MediaType.APPLICATION_JSON)
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Employee employee) {
		
		this.userService.updateUser(employee);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
