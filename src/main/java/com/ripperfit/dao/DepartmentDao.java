package com.ripperfit.dao;

import java.util.List;

import com.ripperfit.model.Department;
import com.ripperfit.model.Organization;

public interface DepartmentDao  {
	
	public boolean addDepartment(Department department);
	
	public Department getHelpdeskDepartmentByOrganization(Organization organization);
	
	public Department getAdminDepartmentByOrganization(Organization organization);
	
	public List<Department> viewAllDepartments();
	
	public List<Department> getDepartmentByName(String departmentName);
	
	public List<Department> getDepartmentListByNameInOrganization(String departmentName,Organization organization);
	
	public Department getDepartmentBynameInOrganization(String departmentName , Organization organization);
	
	public Department getDepartmentById(int id);
	
	public boolean updateDepartment(Department department);
	
	public List<Department> getAllDepartmentsInAnOrganization(Organization organization);

}