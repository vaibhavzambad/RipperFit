package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.model.Designation;

public class DesignationDao {

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

	public int getParentDesignationIdByDesignationId(int designationId){

		Session session = this.getSessionFactory().getCurrentSession();

		String hql = "SELECT parent_designation_id FROM Designation WHERE designation_id = :designationId";
		Query query = session.createQuery(hql);
		query.setParameter("designationId", designationId);
		int parentDesignationId = query.executeUpdate();
		return parentDesignationId;		
	}

	public int getParentDesignationIdByDesignationName(String designationName){

		Session session = this.getSessionFactory().getCurrentSession();

		String hql = "SELECT parent_designation_id FROM Designation WHERE designation_name = :designationName";
		Query query = session.createQuery(hql);
		query.setParameter("designationName", designationName);
		int parentDesignationId = query.executeUpdate();
		return parentDesignationId;		
		
	}
	public List<Designation> viewAllRoles() {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Designation> des = session.createCriteria(Designation.class).list();
		return des;
	}

	
}
