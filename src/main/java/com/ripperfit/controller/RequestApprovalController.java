package com.ripperfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.service.RequestApprovalService;

@RequestMapping(value = "/approve")
@RestController
public class RequestApprovalController {

	private RequestApprovalService requestApprovalService;

	/**
	 * @return the requestApprovalService
	 */
	public RequestApprovalService getRequestApprovalService() {
		return requestApprovalService;
	}



	/**
	 * @param requestApprovalService the requestApprovalService to set
	 */
	@Autowired(required=true)
	public void setRequestApprovalService(
			RequestApprovalService requestApprovalService) {
		this.requestApprovalService = requestApprovalService;
	}


	@RequestMapping(value = "/forwardRequest/{requestId}", method = RequestMethod.GET)
	public ResponseEntity<Void> forwardRequest(@PathVariable("requestId") int requestId) {

		System.out.println("forward");
		if(this.requestApprovalService.forwardRequest(requestId)){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
