package com.ripperfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.model.Department;
import com.ripperfit.service.DepartmentService;

@RequestMapping(value = "/departments")
@RestController
public class DepartmentController {

	private DepartmentService departmentService;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	@Autowired(required = true)
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	

	@RequestMapping(value = "/getDepartments", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> getDepartments() {

		List<Department> list = this.departmentService.getAllDepartment();
		if(list.isEmpty()) {

			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Department>>(list, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/addDepartment",method = RequestMethod.POST)
	public ResponseEntity<Void> addDepartment(@RequestBody Department department){
       System.out.println("in add department controller");
		int result = this.departmentService.addDepartment(department);
		if(result == 1) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else if(result == 2) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}
