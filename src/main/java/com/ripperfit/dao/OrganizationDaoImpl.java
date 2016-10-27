package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Organization;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {
	
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

	@SuppressWarnings("unchecked")
	public List<Organization> getAllOrganizations() {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Organization.class).list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public boolean addOrganization(Organization organization) {

		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.save(organization);
			return true;
		} catch(Exception e) {
			throw e;
		}finally{
		
		}
	}

	public Organization getOrganizationByName(String organizationName){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Organization where organizationName= :organizationName"); 
			query.setParameter("organizationName", organizationName);
			return (Organization) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public Organization getOrganizationById(int organizationId){
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Organization where organizationId= :organizationId"); 
			query.setParameter("organizationId", organizationId);
			return (Organization) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}	
}