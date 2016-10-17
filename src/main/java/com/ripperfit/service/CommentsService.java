package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.dao.CommentDao;
import com.ripperfit.model.Comments;
import com.ripperfit.model.ResourceRequest;



public class CommentsService {

private CommentDao commentDao;
	
	/**
	 * method to get the ResourceDAO object
	 * @return ResourceDAO object
	 */
	public CommentDao getCommentDao() {
		return commentDao;
	}

	/**
	 * method to set the ResourceDAO object
	 * @param resourceDao object
	 */
	@Autowired(required=true)
	public void setCommentDao(CommentDao commentDao) {
		
		this.commentDao = commentDao;
	}

	@Transactional
	public List<Comments> getCommentByRequestId(ResourceRequest resourceRequest){
		
		List<Comments> commentList  = this.commentDao.getCommentByRequestId(resourceRequest);
		return commentList;
	}
	
}
