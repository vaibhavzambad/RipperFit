package com.ripperfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.model.Organization;
import com.ripperfit.service.OrganizationService;

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
	
	@RequestMapping(value = "/getAllOrganizations", method = RequestMethod.GET)
	public ResponseEntity<List<Organization>> getAllOrganizations() {
		
		List<Organization> organizationList = this.organizationService.getAllOrganizations();
		
		if(organizationList.isEmpty()) {

			return new ResponseEntity<List<Organization>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Organization>>(organizationList, HttpStatus.OK);
		}
	}
	
}
