package com.ripperfit.dao;

import java.util.List;

import com.ripperfit.model.Designation;
import com.ripperfit.model.Employee;
import com.ripperfit.model.Organization;

public interface UserDao {
	
	public boolean registerEmployee(Employee employee);
	
	public Employee getEmployeeById(int id);
	
	public Employee getEmployeeByEmail(String email);
	
	public boolean updateEmployee(Employee employee);
	
	public Employee login(String email, String password);

	public List<Employee> viewAllEmployee();

	public Employee getEmployeeByDesignation(Designation designation);
	
	public List<Employee> getEmployeeApprove(Employee employee);
	
	public List<Employee> getAllEmployeesInAnOrganization(Organization organization);
	
}