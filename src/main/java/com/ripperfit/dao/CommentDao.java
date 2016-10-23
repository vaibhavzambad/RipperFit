package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Comments;
import com.ripperfit.model.ResourceRequest;

@Repository
public class CommentDao {


	private SessionFactory sessionFactory;

	/**
	 * method to get SessionFactory object
	 * @return : SessionFactory object
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * method to set SessionFactory object
	 * @param sessionFactory
	 */
	@Autowired(required=true)
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * method to view comments by an resource request

	 * @return : list of comments
	 */

	@SuppressWarnings("unchecked")
	public List<Comments> getCommentByRequestId(ResourceRequest resourceRequest) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			List<Comments> commentList = (List<Comments>)session.createCriteria(Comments.class)
					.add(Restrictions.eq("resourceRequest.requestId",resourceRequest.getRequestId())).list();
			return commentList;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}

	}

	public Boolean addComment(Comments comment) {

		boolean result = false;
		int i=0;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			i = (Integer) session.save(comment);
			if(i > 0) {
				result = true;
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}