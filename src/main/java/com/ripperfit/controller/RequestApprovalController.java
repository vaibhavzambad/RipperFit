package com.ripperfit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ripperfit.service.RequestApprovalService;

/**
 * Controller to handle resource approval 
 */
@RequestMapping(value = "/approve")
@RestController
public class RequestApprovalController {

	private RequestApprovalService requestApprovalService;

	/**
	 * Method to get RequestApprovalService object
	 * @return RequestApprovalService object
	 */
	public RequestApprovalService getRequestApprovalService() {
		return requestApprovalService;
	}

	/**
	 * Method to set requestApprovalService object
	 * @param requestApprovalService object to set
	 */
	@Autowired(required=true)
	public void setRequestApprovalService(
			RequestApprovalService requestApprovalService) {
		this.requestApprovalService = requestApprovalService;
	}
	
	/**
	 * Method to forward a request to parent for approval by request ID
	 * @param requestId : ID of request
	 * @return ResponseEntity with no object
	 */
	@RequestMapping(value = "/forwardRequest/{requestId}", method = RequestMethod.GET)
	public ResponseEntity<Void> forwardRequest(@PathVariable("requestId") int requestId) {

		if(this.requestApprovalService.forwardRequest(requestId)){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Method to reject a particular request by requestId
	 * @param requestId : Id of request to be rejected
	 * @return ResponseEntity with no object
	 */
	@RequestMapping(value = "/rejectRequest/{requestId}", method = RequestMethod.GET)
	public ResponseEntity<Void> rejectRequest(@PathVariable("requestId") int requestId) {
		
		if(this.requestApprovalService.rejectRequest(requestId)){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Method to complete a request after delivery of requested resource
	 * @param requestId : id of request
	 * @return ResponseEntity with no object
	 */
	@RequestMapping(value="/completeRequest/{requestId}",method = RequestMethod.GET)
	public ResponseEntity<Void> completeRequest(@PathVariable("requestId") int requestId){

		if(this.requestApprovalService.completeRequest(requestId)){
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
