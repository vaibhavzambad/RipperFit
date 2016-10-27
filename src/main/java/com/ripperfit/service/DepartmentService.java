package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.CustomExceptions.DepartmentAlreadyPresentException;
import com.ripperfit.CustomExceptions.DepartmentDoesNotExistsException;
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
	public List<Department> getAllDepartment() throws Exception{

		try{
			List<Department> departmentList = this.departmentDao.viewAllDepartments();
			if(departmentList.isEmpty()){
				throw new DepartmentDoesNotExistsException("Department does not exists");
			}
			return departmentList;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public boolean addDepartmentByOrganization(Department department,Organization organization) throws Exception {

		try{
			Department departmentInOrganization = this.departmentDao.getDepartmentBynameInOrganization(department.getDepartmentName(), organization);
			if(departmentInOrganization != null){
				throw new DepartmentAlreadyPresentException("Department Already Present");
			}
			this.departmentDao.addDepartment(department);
		}catch(Exception ex){
			throw ex;
		}
		return true;
	}

	/**
	 * method to get employee by id
	 * @param id : employee id
	 * @return Employee object
	 * @throws Exception 
	 */
	@Transactional
	public Department getDepartmentById(int id) throws Exception {

		try{
			Department department = this.departmentDao.getDepartmentById(id);
			if(department == null){
				throw new DepartmentDoesNotExistsException("Department does not exists");
			}
			return department;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public boolean updateDepartment(Department department) throws Exception {

		try{
			if(getDepartmentById(department.getDepartmentId()) == null){
				throw new DepartmentDoesNotExistsException("Department does not exists");
			}
			return this.departmentDao.updateDepartment(department);
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public List<Department> getAllDepartmentsInAnOrganization(Organization organization) throws Exception{

		try{
			List<Department> departmentsList = this.departmentDao.getAllDepartmentsInAnOrganization(organization);
			if(departmentsList.isEmpty()){
				throw new DepartmentDoesNotExistsException("Department does not exists");
			}
			return departmentsList;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public Department getDepartmentInAnOrganization(String departmentName,Organization organization) throws Exception
	{
		try{
			Department department = this.departmentDao.getDepartmentBynameInOrganization(departmentName, organization);
			if(department == null){
				throw new DepartmentDoesNotExistsException("Department does not exists");
			}
			return department;
		}catch(Exception ex){
			throw ex;
		}
	}
}