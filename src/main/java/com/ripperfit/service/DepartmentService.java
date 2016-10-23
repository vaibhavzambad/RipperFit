package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.dao.DepartmentDao;
import com.ripperfit.model.Department;
import com.ripperfit.model.Organization;

@Transactional
public class DepartmentService {

	private DepartmentDao departmentDao;

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	@Autowired(required=true)
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	@Transactional
	public List<Department> getAllDepartment()
	{
		List<Department> departments=this.departmentDao.viewAllDepartments();
		return departments;
	}
	
	@Transactional
	public int addDepartmentByOrganization(Department department,Organization organization) {
		
		int result = 0;
		Department departmentInOrganization = this.departmentDao.getDepartmentBynameInOrganization(department.getDepartmentName(), organization);
		 if(departmentInOrganization != null){
			 result = 1;
		 }
		 else if(this.departmentDao.addDepartment(department)) {
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
	public Department getDepartmentById(int id) {
		
		Department department = this.departmentDao.getDepartmentById(id);
		return department;
	}

	@Transactional
	public void updateDepartment(Department department) {
		
		this.departmentDao.updateDepartment(department);
	}
	
	@Transactional
	public List<Department> getAllDepartmentsInAnOrganization(Organization organization)
	{
		List<Department> department=this.departmentDao.getAllDepartmentsInAnOrganization(organization);
		return department;
	}
	
	@Transactional
	public Department getDepartmentInAnOrganization(String departmentName,Organization organization)
	{
		
		Department department = this.departmentDao.getDepartmentBynameInOrganization(departmentName, organization);
		return department;
	}
	
}
	

