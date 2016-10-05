package com.ripperfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ripperfit.model.ResourceRequest;
import com.ripperfit.service.ResourceRequestService;

/**
 * controller class to manipulate Resource Requests
 */
@RequestMapping(value = "/request")
@RestController
public class ResourceRequestController {

	private ResourceRequestService resourceRequestService;
	
	/**
	 * method to get ResourceRequestService object
	 * @return : ResourceRequestService object
	 */
	public ResourceRequestService getResourceRequestService() {
		return resourceRequestService;
	}

	/**
	 * method to set ResourceRequestService object
	 * @param resourceRequestService
	 */
	@Autowired(required=true)
	public void setResourceRequestService(ResourceRequestService resourceRequestService) {
		this.resourceRequestService = resourceRequestService;
	}

	/**
	 * method to add resource request
	 * @param request : ResourceRequest object
	 * @param ucBuilder : UriComponentsBuilder object
	 * @return : ResponseEntity blank object
	 */
	@RequestMapping(value = "/addRequest/{employee_id}", method = RequestMethod.POST)
	public ResponseEntity<Void> addResourceRequest(@RequestBody ResourceRequest request, UriComponentsBuilder ucBuilder) {
		
        resourceRequestService.addResourceRequest(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(request.getRequest_id()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	/**
	 * method to delete resource request
	 * @param request : ResourceRequest object
	 * @return : ResponseEntity blank object
	 */
	@RequestMapping(value = "/deleteRequest/{request_id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResourceRequest> deleteResourceRequest(@PathVariable("request_id") int request_id) {
		
		this.resourceRequestService.deleteResourceRequest(request_id);
		 return new ResponseEntity<ResourceRequest>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * method to view individual's resource request
	 * @param emp : Employee object(individual)
	 * @return : ResponseEntity object with list of ResourceRequest objects
	 */
	@RequestMapping(value = "/viewRequests/{employee_id}", method = RequestMethod.GET)
	public ResponseEntity<List<ResourceRequest>> viewResourceRequest(@PathVariable("employee_id") int employee_id) {
		
		List<ResourceRequest> list = this.resourceRequestService.viewResourceRequest(employee_id);
		if(list.isEmpty()) {

			return new ResponseEntity<List<ResourceRequest>>(HttpStatus.NO_CONTENT);
		} else {
			
			return new ResponseEntity<List<ResourceRequest>>(list, HttpStatus.OK);
		}
	}
	
	/**
	 * method to view all resource request
	 * @return : ResponseEntity object with list of ResourceRequest objects
	 */
	@RequestMapping(value = "/viewAllRequests", method = RequestMethod.GET)
	public ResponseEntity<List<ResourceRequest>> viewAllResourceRequest() {
		
		List<ResourceRequest> list = this.resourceRequestService.viewAllResourceRequest();
		if(list.isEmpty()) {

			return new ResponseEntity<List<ResourceRequest>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<ResourceRequest>>(list, HttpStatus.OK);
		}
	}
}