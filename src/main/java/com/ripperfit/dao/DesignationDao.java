package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Designation;

@Repository
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

		String hql = "SELECT parent_designation_id FROM Designation WHERE designationId = :designationId";
		Query query = session.createQuery(hql);
		query.setParameter("designationId", designationId);
		int parentDesignationId = query.executeUpdate();
		return parentDesignationId;		
	}

	public int getParentDesignationIdByDesignationName(String designationName){

		Session session = this.getSessionFactory().getCurrentSession();

		String hql = "SELECT parent_designation_id FROM Designation WHERE designationName = :designationName";
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

	public Boolean addDesignation(Designation designation) {
		
		Session session = this.sessionFactory.getCurrentSession();
		boolean result = false;
		int i=0;
		try {
			i = (Integer) session.save(designation);
			if(i > 0) {
				result = true;
			}
		} catch(Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public boolean deleteDesignationById(int designationId){
		
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "delete from Designation where designationId= :designationId";
		Query query = session.createQuery(hql);
		query.setParameter("designationId", designationId);
		query.executeUpdate();
		return true;

	}
	
	public Designation getDesignationById(int designationId){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Designation where designationId= :designationId"); 
		query.setParameter(" designationId", designationId);
		@SuppressWarnings("unchecked")
		List<Designation> designationList = query.list();
		Designation designation = (Designation) designationList.get(0);
		return designation;
		
	}
	
	public boolean deleteDesignationByName(String designationName){

		Session session = this.sessionFactory.getCurrentSession();
		String hql = "delete from Designation where designationName= :designationName";
		Query query = session.createQuery(hql);
		query.setParameter("designationName", designationName);
		query.executeUpdate();
		return true;

	}
	
	public Designation getDesignationByName(String designationName){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Designation where designationName= :designationName"); 
		query.setParameter(" designationName", designationName);
		@SuppressWarnings("unchecked")
		List<Designation> designationList = query.list();
		Designation designation = (Designation) designationList.get(0);
		return designation;
		
	}

}