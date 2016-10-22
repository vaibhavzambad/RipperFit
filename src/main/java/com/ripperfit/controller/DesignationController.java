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
import com.ripperfit.model.Designation;
import com.ripperfit.model.Organization;
import com.ripperfit.service.DepartmentService;
import com.ripperfit.service.DesignationService;
import com.ripperfit.service.OrganizationService;

@RequestMapping(value = "/designation")
@RestController
public class DesignationController {

	private DesignationService designationService;
	private OrganizationService organizationService;
	private DepartmentService departmentService;

	/**
	 * 
	 * @return
	 */
	public DesignationService getDesignationService() {
		return designationService;
	}

	/**
	 * 
	 * @param designationService
	 */
	@Autowired(required=true)
	public void setDesignationService(DesignationService designationService) {
		this.designationService = designationService;
	}

	/**
	 * 
	 * @return
	 */
	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	/**
	 * 
	 * @param organizationService
	 */
	@Autowired(required=true)
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	
	
	/**
	 * @return the departmentService
	 */
	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	/**
	 * @param departmentService the departmentService to set
	 */
	@Autowired(required=true)
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value="/addDesignation",method=RequestMethod.POST)
	public ResponseEntity<Void> addDesignation(@RequestBody Designation designation){

		int result = this.designationService.addDesignation(designation, designation.getOrganization());
		if(result == 1) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else if(result == 2) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value="/getDesignationByName/{designationName}/{organizationId}",method=RequestMethod.GET)
	public ResponseEntity<Designation> getDesignationByName(@PathVariable("designationName") String designationName , @PathVariable("organizationId") String organizationId){
		Organization organization = this.organizationService.getOrganizationById(Integer.parseInt(organizationId));
		
		Designation designation = this.designationService.getDesignationInAnOrganization(designationName,organization);
		if(designation != null){
			return new ResponseEntity<Designation>(designation,HttpStatus.OK);
		}else{
			return new ResponseEntity<Designation>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value="/getDesignationById/{designationId}",method=RequestMethod.GET)
	public ResponseEntity<Designation> getDesignationById(@PathVariable("designationId") int designationId)
	{
		System.out.println("des");
		Designation designation = this.designationService.getDesignationById(designationId);
		if(designation != null){
			return new ResponseEntity<Designation>(designation,HttpStatus.OK);
		}else{
			return new ResponseEntity<Designation>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value="/deleteDesignationByName",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDesignationByName(@RequestBody String designationName){

		boolean result = this.designationService.deleteDesignationByName(designationName);
		if(result){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value="/deleteDesignationById",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteDesignationById(@RequestBody int designationId){

		boolean result = this.designationService.deleteDesignationById(designationId);
		if(result){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value="/updateLevels",method=RequestMethod.PUT)
	public ResponseEntity<Void> updateDesignationLevels(@RequestBody Designation designation){

		this.designationService.updateDesignationLevels(designation);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * done
	 * @param organization
	 * @return
	 */
	@RequestMapping(value = "/getDesignations/{organizationId}", method = RequestMethod.GET)
	public ResponseEntity<List<Designation>> getDesignations(@PathVariable("organizationId") int organizationId) {

		Organization organization = this.organizationService.getOrganizationById(organizationId);
		List<Designation> list = this.designationService.getAllDesignationsInAnOrganization(organization);
		if(list.isEmpty()) {
			return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Designation>>(list, HttpStatus.OK);
		}
	}
	
	/**
	 * done
	 * @param organization
	 * @return
	 */
	@RequestMapping(value = "/getDesignationsByDepartment/{departmentId}", method = RequestMethod.GET)
	public ResponseEntity<List<Designation>> getDesignationsByDepartment(@PathVariable("departmentId") int departmentId) {

		Department department = this.departmentService.getDepartmentById(departmentId);
		List<Designation> list = this.designationService.getDesignationsInDepartment(department);
		if(list.isEmpty()) {
			return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Designation>>(list, HttpStatus.OK);
		}
	}
	@RequestMapping(value = "/updateDesignation", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Designation designation) {
		
		this.designationService.updateDesignation(designation);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}