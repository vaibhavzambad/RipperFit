package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.dao.UserDao;
import com.ripperfit.model.Employee;

@Transactional
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * method to get the UserDao object
	 * @return UserDAO object
	 */
	public UserDao getUserDao() {
		return userDao;
	}
	
	/**
	 * method to set the UserDao object
	 * @param UserDao object
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
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
	
	@Transactional
	public List<Employee> viewAllEmployee() {
		// TODO Auto-generated method stub
		List<Employee> emp=this.userDao.viewAllEmployee();
		return emp;
	}
	
	@Transactional
	public boolean deleteEmployeeById(int employeeId){
		
		boolean result = this.userDao.deleteEmployeeById(employeeId);
		return result;
		
	}
}