package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
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
	public int addDepartment(Department department) {
		System.out.println(department.getDepartmentName());
		int result = 0;

		if(this.departmentDao.getDepartmentByName(department.getDepartmentName()).size() != 0) {
			System.out.println("jhjh");
			result = 1;
		} else if(this.departmentDao.addDepartment(department)) {
			System.out.println("hgjhg");
			result = 2;
		}
		return result;
	}
	
	@Transactional
	public int addDepartmentByOrganization(Department department,Organization organization) {
		
		System.out.println("in add department by organization"+organization.getOrganizationName());
		System.out.println(department.getDepartmentName());
		int result = 0;
		if(this.departmentDao.getDepartmentListByNameInOrganization(department.getDepartmentName(),organization).size() != 0) {
			System.out.println("jhjh");
			System.out.println("conflisct is present");
			result = 1;
		} else if(this.departmentDao.addDepartment(department)) {
			System.out.println("no conflict");
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
	
	/**
	 * done
	 * @return
	 */
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
	

