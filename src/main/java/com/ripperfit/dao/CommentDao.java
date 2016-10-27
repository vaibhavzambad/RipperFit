package com.ripperfit.dao;

import java.util.List;

import com.ripperfit.model.Comments;
import com.ripperfit.model.ResourceRequest;

public interface CommentDao {
	
	public List<Comments> getCommentByRequestId(ResourceRequest resourceRequest) ;
	
	public boolean addComment(Comments comment);

}
