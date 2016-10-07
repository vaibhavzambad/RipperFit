package com.ripperfit.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.daoLayer.UserDao;
import com.ripperfit.model.Employee;

<<<<<<< HEAD
@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

=======

@Transactional
public class UserService {


	@Autowired
	private UserDao userDao;
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
	/**
	 * method to get the UserDao object
	 * @return UserDAO object
	 */
	public UserDao getUserDao() {
		return userDao;
	}
<<<<<<< HEAD
	
=======
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
	/**
	 * method to set the UserDao object
	 * @param UserDao object
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
<<<<<<< HEAD
	
	/**
	 * method to register employee
	 * @param employee : employee object
	 * @return boolean status
	 */
	@Transactional
	public int registerEmployee(Employee employee) {
		
		int result = 0;
		if(this.userDao.getEmployeeByEmail(employee.getEmail()) != null) {
			result = 1;
		} else if(this.userDao.registerEmployee(employee)) {
			result = 2;
		}
		return result;
	}
	
	/**
	 * method to get employee by id
	 * @param id : employee id
	 * @return Employee object
	 */
	@Transactional
	public Employee getEmployeeById(int id) {
		
		Employee emp = this.userDao.getEmployeeById(id);
		return emp;
	}
	
	/**
	 * method to get employee by email
	 * @param email : employee's email address
	 * @return Employee object
	 */
	@Transactional
	public Employee getEmployeeByEmail(String email) {
		
		Employee emp = this.userDao.getEmployeeByEmail(email);
		return emp;
	}
	
	/**
	 * method to update employee
	 * @param employee : employee object
	 */
	@Transactional
	public void updateUser(Employee employee) {
		
		this.userDao.updateEmployee(employee);
	}

	/**
	 * method to login the employee
	 * @param email : employee's email address
	 * @param password employee's password
	 * @return : Employee object
	 */
	@Transactional
	public Employee login(String email,String password) {
		
		Employee emp = this.userDao.login(email,password);
		return emp;
	}
}
=======
	/**
	 * method to register employee
	 * @return boolean status
	 */
	@Transactional
	public int registerUser(Employee employee)
	{
		int result = 0;
		if( this.ifUserExists(employee.getEmail()) ){
			result = 1;
		} else if(this.userDao.registerUser(employee)) {
			result = 2;
		}
		
		return result;
	}
	/**
	 * method to exists that employee exists or not
	 * @return boolean status
	 */
	public Boolean ifUserExists(String email) {
		Boolean flag=false;
		flag=this.userDao.ifUserExists(email);
		return flag;
	}
	/**
	 * method to get employee by id
	 * @return Employee object
	 */
	@Transactional
	public Employee getUserById(int id)
	{
		Employee emp=this.userDao.getUserById(id);
		return emp;

	}
	/**
	 * method to get employee by email
	 * @return Employee object
	 */
	@Transactional
	public Employee getUserByEmail(String email)
	{
		Employee emp=this.userDao.getUserByEmail(email);
		return emp;

	}
	/**
	 * method to update employee

	 */
	@Transactional
	public void updateUser(Employee employee)
	{
		this.userDao.updateUser(employee);
	}

	@Transactional
	public Employee login(String email,String password)
	{
		Employee emp=this.userDao.login(email,password);
		return emp;

	}

}
>>>>>>> 5b44efbe0d5ce37771e7fe72fc2d0a57e353c5bd
