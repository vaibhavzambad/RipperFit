package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Department;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Organization;

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

	/**
	 * done
	 * @return
	 */
	public List<Designation> getAllDesignationsInAnOrganization(Organization organization) {

		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Designation> des = session.createCriteria(Designation.class).add( Restrictions.eq("organization",organization)).list();
		return des;
	}
	
	/**
	 * done
	 * @return
	 */
	public List<Designation> getDesignationsInDepartment(Department department) {

		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Designation> des = session.createCriteria(Designation.class).add( Restrictions.eq("department",department)).list();
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
	
	public Designation getDesignationByDepartment(Department department){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Designation where department= :department"); 
		query.setParameter("department", department);
		Designation designation = (Designation) query.uniqueResult();
		return designation;
	}

	public void updateLevels(List<Designation> designationList) {

		Session session = this.sessionFactory.getCurrentSession();
		for(Designation des : designationList) {
			session.update(des);
		}
	}
	
	public List<Designation> designationListAboveLevel(Designation designation) {
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Designation where designationLevel >= :designationLevel and department = :department"); 
		query.setParameter("designationLevel", designation.getDesignationLevel()+1);
		query.setParameter("department", designation.getDepartment());
		@SuppressWarnings("unchecked")
		List<Designation> designationList = query.list();
		return designationList;
	}
	
public Designation getDesignationBynameInOrganization(String designationName , Organization organization){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Designation where designationName= :designationName and organization= :organization"); 
		query.setParameter("designationName", designationName);
		query.setParameter("organization", organization);
		Designation designation = null;
		@SuppressWarnings("unchecked")
		List<Designation> designationList = query.list();
		if(! designationList.isEmpty()){
			
			designation = designationList.get(0);
		}
		
		return designation;
	}
}