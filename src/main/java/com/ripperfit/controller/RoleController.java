package com.ripperfit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.model.Designation;
import com.ripperfit.model.ResourceRequest;
import com.ripperfit.service.RoleService;
import com.ripperfit.service.UserService;

@RequestMapping(value = "/role")
@RestController
public class RoleController {

	private RoleService roleService;

	/**
	 * method to get UserServiceObject
	 * @return : UserService object
	 */
	public RoleService roleService() {
		return roleService;
	}

	/**
	 * method to set UserService object
	 * @param UserService
	 */
	@Autowired(required = true)
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * method to view all resource request
	 * @return : ResponseEntity object with list of ResourceRequest objects
	 */
	@RequestMapping(value = "/getDesignations", method = RequestMethod.GET)
	public ResponseEntity<List<Designation>> viewRoles() {
		
		List<Designation> list = this.roleService.viewAllRoles();
		if(list.isEmpty()) {

			return new ResponseEntity<List<Designation>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Designation>>(list, HttpStatus.OK);
		}
	}
}
