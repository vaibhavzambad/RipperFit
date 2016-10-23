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

import com.ripperfit.model.Comments;
import com.ripperfit.model.ResourceRequest;
import com.ripperfit.service.CommentsService;
import com.ripperfit.service.ResourceRequestService;

/**
 * controller class to deal with all views related to comments
 */
@RequestMapping(value = "/comment")
@RestController
public class CommentController {

	private ResourceRequestService resourceRequestService;
	private CommentsService commentsService;

	/**
	 * method to get CommentsService object
	 * @return :CommentsService object
	 */
	public CommentsService getCommentsService() {
		return commentsService;
	}

	/**
	 * method to set CommentsServiceobject
	 * @param CommentsService
	 */
	@Autowired(required=true)
	public void setCommentsService(CommentsService commentsService) {
		this.commentsService = commentsService;
	}
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
	 * method to add comments
	 * @param comment : Comment object
	 * @return : ResponseEntity blank object
	 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public ResponseEntity<Void> addComment(@RequestBody Comments comment) {

		Boolean flag=commentsService.addComment(comment);
		if(flag==true)
		{
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	/**
	 * method to view individual's resource request comments
	 * @return : ResponseEntity object with list of commentsobjects
	 */
	@RequestMapping(value = "/getCommentByRequestId/{requestId}", method = RequestMethod.GET)
	public ResponseEntity<List<Comments>> getCommentByRequestId(@PathVariable("requestId") int requestId) {

		ResourceRequest resourceRequest = this.resourceRequestService.getResourceRequestById(requestId);

		List<Comments> commentList  = this.commentsService.getCommentByRequestId(resourceRequest);
		if(commentList.isEmpty()) {
			return new ResponseEntity<List<Comments>>(HttpStatus.NO_CONTENT);
		} else {

			return new ResponseEntity<List<Comments>>(commentList, HttpStatus.OK);
		}
	}

}