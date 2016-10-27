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

import com.ripperfit.CustomExceptions.DepartmentDoesNotExistsException;
import com.ripperfit.CustomExceptions.DesignationAlreadyPresentException;
import com.ripperfit.CustomExceptions.DesignationsDoesNotExistsException;
import com.ripperfit.CustomExceptions.OrganizationDoesNotExistsException;
import com.ripperfit.model.Department;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Organization;
import com.ripperfit.service.DepartmentService;
import com.ripperfit.service.DesignationService;
import com.ripperfit.service.OrganizationService;

/**
 * Controller class for designation
 */
@RequestMapping(value = "/designation")
@RestController
public class DesignationController {

	private DesignationService designationService;
	private OrganizationService organizationService;
	private DepartmentService departmentService;

	/**
	 * Getter method to get DesignationService object
	 * @return DesignationService object
	 */
	public DesignationService getDesignationService() {
		return designationService;
	}

	/**
	 * Setter method to set DesignationService object
	 * @param designationService object
	 */
	@Autowired(required=true)
	public void setDesignationService(DesignationService designationService) {
		this.designationService = designationService;
	}
	/**
	 * Getter method to get organizationService object
	 * @return organizationService object
	 */
	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	/**
	 * Setter method to set organizationService object
	 * @param organizationService object
	 */
	@Autowired(required=true)
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	/**
	 * Getter method to get DepartmentService object
	 * @return DepartmentService object
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * Setter method to set DepartmentService
	 * @param departmentService : departmentService to set
	 */
	@Autowired(required=true)
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	/**
	 * Method to add a new designation in organization
	 * @param designation : Designation object to be added
	 * @return ResponseEntity with no object
	 */
	@RequestMapping(value="/addDesignation",method=RequestMethod.POST)
	public ResponseEntity<Void> addDesignation(@RequestBody Designation designation){

		try{
			this.designationService.addDesignation(designation, designation.getOrganization());
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}catch(DesignationAlreadyPresentException designationAlreadyPresentException){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}catch(Exception ex){
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to get a designation of a particular organization by name of designation
	 * @param designationName : name of designation
	 * @param organizationId : ID of that organization
	 * @return ResponseEntity with designation object
	 */
	@RequestMapping(value="/getDesignationByName/{designationName}/{organizationId}",method=RequestMethod.GET)
	public ResponseEntity<Designation> getDesignationByName(@PathVariable("designationName") String designationName , @PathVariable("organizationId") String organizationId){

		try{
			Organization organization = this.organizationService.getOrganizationById(Integer.parseInt(organizationId));
			Designation designation = this.designationService.getDesignationInAnOrganization(designationName,organization);
			return new ResponseEntity<Designation>(designation,HttpStatus.OK);
		}catch(OrganizationDoesNotExistsException | DesignationsDoesNotExistsException notExistsException){
			return new ResponseEntity<Designation>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<Designation>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to get a designation by its Id
	 * @param designationId : id of designation to be found
	 * @return ResponseEntity with designation object
	 */
	@RequestMapping(value="/getDesignationById/{designationId}",method=RequestMethod.GET)
	public ResponseEntity<Designation> getDesignationById(@PathVariable("designationId") int designationId)
	{

		try{
			Designation designation = this.designationService.getDesignationById(designationId);
			return new ResponseEntity<Designation>(designation,HttpStatus.OK);
		}catch(DesignationsDoesNotExistsException designationsDoesNotExistsException){
			return new ResponseEntity<Designation>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<Designation>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to update level of existing designations after adding a new designation
	 * @param designation : Designation which is added
	 * @return ResponseEntity with no object
	 * @throws Exception 
	 */
	@RequestMapping(value="/updateLevels",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateDesignationLevels(@RequestBody Designation designation) throws Exception{

		try{
			this.designationService.updateDesignationLevels(designation);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(DesignationsDoesNotExistsException designationsDoesNotExistsException){
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to get all designations in an organization by organization ID
	 * @param organizationId : ID of organization
	 * @return ResponseEntity with list of Designation objects
	 */
	@RequestMapping(value = "/getDesignations/{organizationId}", method = RequestMethod.GET)
	public ResponseEntity<List<Designation>> getDesignations(@PathVariable("organizationId") int organizationId) {

		try{
			Organization organization = this.organizationService.getOrganizationById(organizationId);
			List<Designation> designationList = this.designationService.getAllDesignationsInAnOrganization(organization);
			return new ResponseEntity<List<Designation>>(designationList, HttpStatus.OK);
		}catch(OrganizationDoesNotExistsException | DesignationsDoesNotExistsException notExistsException){
			return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<List<Designation>>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to get all designations associated with a particular department
	 * @param departmentId : ID of department
	 * @return ResponseEntity with list of Designation objects
	 */
	@RequestMapping(value = "/getDesignationsByDepartment/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<List<Designation>> getDesignationsByDepartment(@PathVariable("departmentId") int departmentId) {

		try{
			Department department = this.departmentService.getDepartmentById(departmentId);
			List<Designation> list = this.designationService.getDesignationsInDepartment(department);
			return new ResponseEntity<List<Designation>>(list, HttpStatus.OK);
		}catch(DepartmentDoesNotExistsException | DesignationsDoesNotExistsException notExistsException){
			return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<List<Designation>>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to update a designation of organization
	 * @param designation : designation object to be updated
	 * @return : ResponseEntity with no object
	 */
	@RequestMapping(value = "/updateDesignation", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Designation designation) {

		try{
			this.designationService.updateDesignation(designation);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}catch(DesignationsDoesNotExistsException designationsDoesNotExistsException){
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}