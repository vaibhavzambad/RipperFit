package com.ripperfit.controller;

<<<<<<< HEAD
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
=======
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.ResponseBody;
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.model.Employee;
import com.ripperfit.model.Login;
import com.ripperfit.service.UserService;

/**
 * controller class to handle user data
 */
<<<<<<< HEAD
=======

>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
@RequestMapping(value = "/employee")
@RestController
public class UserController {

	private UserService userService;

	/**
	 * method to get UserServiceObject
<<<<<<< HEAD
=======
	 * 
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
	 * @return : UserService object
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * method to set UserService object
<<<<<<< HEAD
=======
	 * 
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
	 * @param UserService
	 */
	@Autowired(required = true)
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
<<<<<<< HEAD
	 * method to get employee with his/her email address
	 * @param email : employee's email address
	 * @return : ResponseEntity with no object
	 */
	@RequestMapping(value = "/getEmployeeByEmail", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeByEmail(@RequestParam String email) {

		Employee employee = this.userService.getEmployeeByEmail(email);
=======
	 * method to get Employee By Email
	 * 
	 * 
	 * @return : Employee Object
	 */

	@RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
	public ResponseEntity<Employee> getUserByEmail(@RequestParam String email) {

		Employee employee = this.userService.getUserByEmail(email);
		System.out.println("mail");
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
		if (employee != null) {
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}
<<<<<<< HEAD
	
	/**
	 * method to get Employee By EmployeeId
	 * @param id : employee id
	 * @return : ResponseEntity with Employee object
	 */
	@RequestMapping(value = "/getEmployeeById", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@RequestParam String Id) {
		
		int id = Integer.parseInt(Id);
		Employee employee = this.userService.getEmployeeById(id);
=======

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
		
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
		if (employee != null) {
			return new ResponseEntity<Employee>(employee,HttpStatus.OK);
		} else {
			return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
		}
	}
	
	/**
	 * method to check that email and password are correct for login
<<<<<<< HEAD
	 * @param login : Login object with email and password
	 * @return : ResponseEntity with no object
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Void> login(@RequestBody Login login,HttpServletRequest request) {	
		
		HttpSession session = request.getSession();
		Employee employee = this.userService.login(login.getEmail(), login.getPassword());
		
		if (employee != null) {
			session.setAttribute("email", login.getEmail());
=======
	 * 
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Void> login(@RequestBody Login login) {
		System.out.println("email: "+login.getEmail());
		System.out.println("password: "+login.getPassword());
		Employee employee = this.userService.login(login.getEmail(), login.getPassword());
		if (employee != null) {
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
			return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
	}
<<<<<<< HEAD
	
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
=======
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
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
	public ResponseEntity<Void> update(@RequestBody Employee employee) {
		
		this.userService.updateUser(employee);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
<<<<<<< HEAD
	
	/**
	 * 
	 */
	@RequestMapping(value="/logout",method= RequestMethod.POST)
	public ResponseEntity<Void> logout(HttpSession session){
		
		String email = (String)session.getAttribute("email");
		System.out.println(email);
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
=======
}
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
