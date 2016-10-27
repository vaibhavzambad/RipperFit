package com.ripperfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.model.Department;
import com.ripperfit.model.Organization;
import com.ripperfit.service.DepartmentService;
import com.ripperfit.service.OrganizationService;
import com.ripperfit.util.AppException;

/**
 * controller class to deal with all views related to departments
 */
@RequestMapping(value = "/departments")
@RestController
public class DepartmentController {

	private DepartmentService departmentService;
	private OrganizationService organizationService;

	/**
	 * Getter method to get department service object
	 * @return : DepartmentService object
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * Method to set DepartmentService object
	 * @param departmentService : DepartmentService object
	 */
	@Autowired(required = true)
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * Method to get organizationService object
	 * @return : OrganizationService object
	 */
	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	/**
	 * Method to set OrganizationService object
	 * @param organizationService : OrganizationService object
	 */
	@Autowired(required=true)
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	/**
	 * Method to get all departments
	 * @return ResponseEntity object with list of department objects
	 */
	@RequestMapping(value = "/getDepartments", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> getDepartments() {

		try{
			List<Department> list = this.departmentService.getAllDepartment();
			return new ResponseEntity<List<Department>>(list, HttpStatus.OK);
		}catch(AppException departmentDoesNotExistsException){
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<List<Department>>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to add new department in organization
	 * @param department : Department object to be added
	 * @return : ResponseEntity with no object
	 */
	@RequestMapping(value="/addDepartment",method = RequestMethod.POST)
	public ResponseEntity<Void> addDepartment(@RequestBody Department department){

		try{
			this.departmentService.addDepartmentByOrganization(department, department.getOrganization());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}catch(AppException departmentAlreadyPresentException){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}catch(Exception ex){
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to get a department by its ID
	 * @param departmentId : Id of department to be found
	 * @return : Department object
	 */
	@RequestMapping(value = "/getDepartmentById/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<Department> getDepartmentById(@PathVariable("departmentId") int departmentId) {

		try{
			Department department = this.departmentService.getDepartmentById(departmentId);
			return new ResponseEntity<Department>(department,HttpStatus.OK);
		}catch(AppException departmentDoesNotExistsException){
			return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<Department>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to update a department by organization
	 * @param department : department object to be updated
	 * @return : ResponseEntity with no object
	 */
	@RequestMapping(value = "/updateDepartment", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Department department) {

		try{
			this.departmentService.updateDepartment(department);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(AppException departmentDoesNotExistsException){
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to get all departments in an organization by organization ID
	 * @param organizationId : ID of organization
	 * @return : ResponseEntity with list of department objects
	 */
	@RequestMapping(value = "/getAllDepartmentsInAnOrganization/{organizationId}", method = RequestMethod.GET)
	public ResponseEntity<List<Department>> getAllDepartmentsInAnOrganization(@PathVariable("organizationId") int organizationId) {
		
		try{
		Organization organization = this.organizationService.getOrganizationById(organizationId);
		List<Department> departmentList = this.departmentService.getAllDepartmentsInAnOrganization(organization);
		return new ResponseEntity<List<Department>>(departmentList, HttpStatus.OK);
		}catch(AppException notExistsException){
			return new ResponseEntity<List<Department>>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<List<Department>>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to get particular department by its name
	 * @param departmentName : name of that department
	 * @param organizationId : Id of organization associated with that department
	 * @return : ResponseEntity with department object
	 */
	@RequestMapping(value = "/getDepartmentByName/{departmentName}/{organizationId}", method = RequestMethod.GET)
	public ResponseEntity<Department> getOrganizationByName( @PathVariable("departmentName") String departmentName , @PathVariable("organizationId") String organizationId) {
		
		try{
		Organization organization = this.organizationService.getOrganizationById(Integer.parseInt(organizationId));
		Department department = this.departmentService.getDepartmentInAnOrganization(departmentName, organization);
		return new ResponseEntity<Department>(department, HttpStatus.OK);
		}catch(AppException notExistsException){
			return new ResponseEntity<Department>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<Department>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}