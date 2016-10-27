package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.dao.CommentDao;
import com.ripperfit.model.Comments;
import com.ripperfit.model.ResourceRequest;
import com.ripperfit.util.AppException;
import com.ripperfit.util.ExceptionConstants;

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
	@Autowired(required = true)
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	@Transactional
	public List<Comments> getCommentByRequestId(ResourceRequest resourceRequest) throws Exception {

		try{
			List<Comments> commentList = this.commentDao.getCommentByRequestId(resourceRequest);
			if(commentList.isEmpty()){
				throw new AppException(ExceptionConstants.COMMENT_NOT_EXIST);
			}
			return commentList;
		}catch(Exception ex){
			throw ex;
		}
	}

	/**
	 * method to add the comment
	 * @param request: Commentobject
	 * **/
	@Transactional
	public boolean addComment(Comments comment) {
		try{
			return this.commentDao.addComment(comment);
		}catch(Exception ex){
			throw ex;
		}
	}
}