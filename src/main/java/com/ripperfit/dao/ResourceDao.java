package com.ripperfit.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResourceDao {
	
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
	
	public int getFinalApprovalDesignationIdByResourceId(int resourceId){
		
		Session session = this.sessionFactory.getCurrentSession();
		
		String hql = "SELECT final_approval_designation_id FROM Resource WHERE resource_id = :resource_id";
		Query query = session.createQuery(hql);
		query.setParameter("resource_id", resourceId);
		int finalApprovalDesignationId = query.executeUpdate();
		return finalApprovalDesignationId;
		
	}
	
	public int getFinalApprovalDesignationIdByResourceName(String resourceName){
		
		Session session = this.sessionFactory.getCurrentSession();
		
		String hql = "SELECT final_approval_designation_id FROM Resource WHERE resource_name = :resource_name";
		Query query = session.createQuery(hql);
		query.setParameter("resource_name", resourceName);
		int finalApprovalDesignationId = query.executeUpdate();
		return finalApprovalDesignationId;
		
	}
	
}