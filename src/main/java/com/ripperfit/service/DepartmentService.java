package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.dao.DepartmentDao;
import com.ripperfit.model.Department;

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

}
