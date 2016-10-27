package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.CustomExceptions.UserAlreadyPresentException;
import com.ripperfit.CustomExceptions.UserNotExistsException;
import com.ripperfit.dao.UserDao;
import com.ripperfit.model.Employee;
import com.ripperfit.model.Organization;

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
	 * @throws Exception 
	 * @throws UserAlreadyPresentException 
	 * method to register employee
	 * @param employee : employee object
	 * @return boolean status
	 * @throws  
	 */
	@Transactional
	public boolean registerEmployee(Employee employee) throws Exception{

		try{
			if(this.userDao.getEmployeeByEmail(employee.getEmail()) != null) {
				throw new UserAlreadyPresentException("User Already Present");	
			}
			return this.userDao.registerEmployee(employee);
		}catch(Exception ex){
			throw ex;
		}
	}

	/**
	 * method to get employee by id
	 * @param id : employee id
	 * @return Employee object
	 * @throws Exception 
	 */
	@Transactional
	public Employee getEmployeeById(int id) throws Exception {

		try{
			Employee employee = this.userDao.getEmployeeById(id);
			if(employee == null){
				throw new UserNotExistsException("User not exists");
			}
			return employee;
		}catch(Exception ex){
			throw ex;
		}
	}

	/**
	 * method to get employee by email
	 * @param email : employee's email address
	 * @return Employee object
	 * @throws Exception 
	 */
	@Transactional
	public Employee getEmployeeByEmail(String email) throws Exception {

		try{
			Employee employee = this.userDao.getEmployeeByEmail(email);
			if(employee == null){
				throw new UserNotExistsException("User not exists");
			}
			return employee;
		}catch(Exception ex){
			throw ex;
		}
	}

	/**
	 * method to update employee
	 * @param employee : employee object
	 * @throws Exception 
	 */
	@Transactional
	public boolean updateUser(Employee employee) throws Exception {
		try{
			if(this.userDao.getEmployeeById(employee.getEmployeeId()) == null){
				throw new UserNotExistsException("User not exists");
			}
			return this.userDao.updateEmployee(employee);
		}catch(Exception ex){
			throw ex;
		}
	}

	/**
	 * method to login the employee
	 * @param email : employee's email address
	 * @param password employee's password
	 * @return : Employee object
	 * @throws Exception 
	 */
	@Transactional
	public Employee login(String email,String password) throws Exception {

		try{
			Employee employee = this.userDao.login(email,password);
			if(employee == null){
				throw new UserNotExistsException("User not exists");
			}
			return employee;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public List<Employee> viewAllEmployee() throws Exception {

		try{
			List<Employee> employeeList = this.userDao.viewAllEmployee();
			if(employeeList.isEmpty()){
				throw new UserNotExistsException("User not exists");
			}
			return employeeList;
		}catch(Exception ex){
			throw ex;
		}
	}

	public List<Employee> getEmployeeApprove(Employee employee) throws Exception {

		try{
			List<Employee> employeeList = this.userDao.getEmployeeApprove(employee);
			if(employeeList.isEmpty()){
				throw new UserNotExistsException("User not exists");
			}
			return employeeList;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public List<Employee> getAllEmployeesInAnOrganization(Organization organization) throws Exception{

		try{
			List<Employee> employeeList = this.userDao.getAllEmployeesInAnOrganization(organization);
			if(employeeList.isEmpty()){
				throw new UserNotExistsException("User not exists");
			}
			return employeeList;
		}catch(Exception ex){
			throw ex;
		}
	}
}