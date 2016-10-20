package com.ripperfit.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		List<Comments> commentList = (List<Comments>)session.createCriteria(Comments.class)
				.add(Restrictions.eq("resourceRequest.requestId",resourceRequest.getRequestId())).list();
		
		System.out.println("df: "+commentList);
		return commentList;
	
	}

		public Boolean addComment(Comments comment) {
			Session session = this.sessionFactory.getCurrentSession();
			boolean result = false;
			int i=0;
			try {
				i = (Integer) session.save(comment);
				if(i > 0) {
					result = true;
				}
			} catch(Exception e) {

				e.printStackTrace();
			}
			return result;
			
		}
}