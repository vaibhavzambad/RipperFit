package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.dao.CommentDao;
import com.ripperfit.model.ApproveRequest;
import com.ripperfit.model.Comments;
import com.ripperfit.model.Employee;
import com.ripperfit.model.ResourceRequest;

public class CommentsService {

	private CommentDao commentDao;

	/**
	 * method to get the ResourceDAO object
	 * 
	 * @return ResourceDAO object
	 */
	public CommentDao getCommentDao() {
		return commentDao;
	}

	/**
	 * method to set the ResourceDAO object
	 * 
	 * @param resourceDao
	 *            object
	 */
	@Autowired(required = true)
	public void setCommentDao(CommentDao commentDao) {

		this.commentDao = commentDao;
	}

	@Transactional
	public List<Comments> getCommentByRequestId(ResourceRequest resourceRequest) {
		System.out.println("comment service");
		List<Comments> commentList = this.commentDao
				.getCommentByRequestId(resourceRequest);
		System.out.println(commentList);
		return commentList;
	}

	/**
	 * method to add the comment
	 * 
	 * @param request
	 *            : Commentobject
	 * **/
	@Transactional
	public Boolean addComment(Comments comment) {

		Boolean flag=this.commentDao.addComment(comment);
		return flag;

	}

}
