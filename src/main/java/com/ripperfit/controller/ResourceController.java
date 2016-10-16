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

import com.ripperfit.model.Resource;
import com.ripperfit.service.ResourceService;

@RequestMapping(value = "/resource")
@RestController
public class ResourceController {

	private ResourceService resourceService;

	/**
	 * @return the resourceService
	 */
	public ResourceService getResourceService() {
		return resourceService;
	}

	/**
	 * @param resourceService the resourceService to set
	 */
	@Autowired(required=true)
	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}

	@RequestMapping(value="/addResource",method = RequestMethod.POST)
	public ResponseEntity<Void> addResource(@RequestBody Resource resource){

		int result = this.resourceService.addResource(resource);
		if(result == 1) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else if(result == 2) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value="/getAllResources",method = RequestMethod.GET)
	public ResponseEntity<List<Resource>> getAllResources(){

		List<Resource> resourceList = this.resourceService.getAllResources();

		if(resourceList.isEmpty()) {

			return new ResponseEntity<List<Resource>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Resource>>(resourceList, HttpStatus.OK);
		}
	}

	@RequestMapping(value="/deleteResourceByName",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteResourceByName(@RequestBody String resourceName){

		boolean result = this.resourceService.deleteResourceByName(resourceName);
		if(result){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	/*@RequestMapping(value="/deleteResourceById",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteResourceById(@RequestParam int resourceId){

		boolean result = this.resourceService.deleteResourceById(resourceId);
		if(result){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}*/

	@RequestMapping(value="/deleteResourceById/{resource_id}",method=RequestMethod.DELETE)
	public ResponseEntity<Void> deleteResourceById(@PathVariable("resource_id") int resource_id){
		System.out.println("dd"+resource_id);
		boolean result = this.resourceService.deleteResourceById(resource_id);
		if(result){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}else{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value="/getResourceById",method=RequestMethod.GET)
	public ResponseEntity<Resource> getResourceById(@RequestBody int resourceId){

		Resource resource = this.resourceService.getResourceById(resourceId);
		if(resource == null){
			return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<Resource>(resource , HttpStatus.OK);
		}
	}



}
