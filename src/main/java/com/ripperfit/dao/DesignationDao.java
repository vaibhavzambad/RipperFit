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
		String hql = "delete from Designation where designation_id= :designationId";
		Query query = session.createQuery(hql);
		query.setParameter("designation_id", designationId);
		query.executeUpdate();
		return true;

	}
	
	public Designation getDesignationById(int designationId){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Designation where designation_id= :designationId"); 
		query.setParameter(" designation_id", designationId);
		@SuppressWarnings("unchecked")
		List<Designation> designationList = query.list();
		Designation designation = (Designation) designationList.get(0);
		return designation;
		
	}
	
	public boolean deleteDesignationByName(String designationName){

		Session session = this.sessionFactory.getCurrentSession();
		String hql = "delete from Designation where designation_name= :designationName";
		Query query = session.createQuery(hql);
		query.setParameter("designation_id", designationName);
		query.executeUpdate();
		return true;

	}
	
	public Designation getDesignationByName(String designationName){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Designation where designation_name= :designationName"); 
		query.setParameter(" designation_name", designationName);
		@SuppressWarnings("unchecked")
		List<Designation> designationList = query.list();
		Designation designation = (Designation) designationList.get(0);
		return designation;
		
	}

}