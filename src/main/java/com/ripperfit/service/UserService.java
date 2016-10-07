package com.ripperfit.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.daoLayer.UserDao;
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
