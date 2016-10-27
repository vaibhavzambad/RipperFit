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

import com.ripperfit.model.Organization;
import com.ripperfit.service.OrganizationService;
import com.ripperfit.util.AppException;

/**
 * Controller class for Organization
 */
@RequestMapping(value = "/organization")
@RestController
public class OrganizationController {

	private OrganizationService organizationService;

	/**
	 * @return the organizationService
	 */
	public OrganizationService getOrganizationService() {
		return organizationService;
	}

	/**
	 * @param organizationService the organizationService to set
	 */
	@Autowired(required=true)
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	/**
	 * Method to get all organizations
	 * @return ResponseEntity with list of Organization objects
	 */
	@RequestMapping(value = "/getAllOrganizations", method = RequestMethod.GET)
	public ResponseEntity<List<Organization>> getAllOrganizations() {

		try{
			List<Organization> organizationList = this.organizationService.getAllOrganizations();
			return new ResponseEntity<List<Organization>>(organizationList, HttpStatus.OK);
		}catch(AppException organizationDoesNotExistsException){
			return new ResponseEntity<List<Organization>>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<List<Organization>>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}	

	/**
	 * Method to add a new organization
	 * @param organization : Organization object to be added
	 * @return ResponseEntity with no object
	 */
	@RequestMapping(value = "/addOrganization", method = RequestMethod.POST)
	public ResponseEntity<Void> addOrganization(@RequestBody Organization organization) {

		try{
			this.organizationService.addOrganization(organization);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}catch(AppException organizationAlreadyPresentException){
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}catch(Exception ex){
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	/**
	 * Method to get a particular organization by its name
	 * @param organizationName : name of organization
	 * @return ResponseEntity with Organization object
	 */
	@RequestMapping(value = "/getOrganizationByName/{organizationName}", method = RequestMethod.GET)
	public ResponseEntity<Organization> getOrganizationByName( @PathVariable("organizationName") String organizationName) {
		
		try{
		Organization organization = this.organizationService.getOrganizationByName(organizationName);
		return new ResponseEntity<Organization>(organization, HttpStatus.OK);
		}catch(AppException organizationDoesNotExistsException){
			return new ResponseEntity<Organization>(HttpStatus.NO_CONTENT);
		}catch(Exception ex){
			return new ResponseEntity<Organization>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}
}