package com.ripperfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.model.Designation;
import com.ripperfit.service.DesignationService;

@RequestMapping(value = "/designation")
@RestController
public class DesignationController {

	private DesignationService designationService;

	/**
	 * method to get UserServiceObject
	 * @return : UserService object
	 */
	public DesignationService designationService() {
		return designationService;
	}

	/**
	 * method to set UserService object
	 * @param UserService
	 */
	@Autowired(required = true)
	public void setRoleService(DesignationService designationService) {
		this.designationService = designationService;
	}

	/**
	 * method to view all resource request
	 * @return : ResponseEntity object with list of ResourceRequest objects
	 */
	@RequestMapping(value = "/getDesignations", method = RequestMethod.GET)
	public ResponseEntity<List<Designation>> getDesignations() {

		List<Designation> list = this.designationService.getAllDesignation();
		if(list.isEmpty()) {

			return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Designation>>(list, HttpStatus.OK);
		}
	}

	@RequestMapping(value="/addDesignation",method=RequestMethod.POST)
	public ResponseEntity<Void> addDesignation(@RequestBody Designation designation){

		int result = this.designationService.addDesignation(designation);
		if(result == 1) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		} else if(result == 2) {
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.SERVICE_UNAVAILABLE);
		}
	}

	@RequestMapping(value="/getDesignationByName",method=RequestMethod.GET)
	public ResponseEntity<Designation> getDesignationByName(@RequestBody String designationName){

		Designation designation = this.designationService.getDesignationByName(designationName);
		if(designation != null){
			return new ResponseEntity<Designation>(designation,HttpStatus.OK);
		}else{
			return new ResponseEntity<Designation>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value="/getDesignationById",method=RequestMethod.GET)
	public ResponseEntity<Designation> getDesignationById(@RequestBody int designationId){

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
}