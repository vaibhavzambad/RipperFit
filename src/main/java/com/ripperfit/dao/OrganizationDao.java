package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Organization;

@Repository
public class OrganizationDao {


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

	public List<Organization> getAllOrganizations() {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Organization> organizationList = session.createCriteria(Organization.class).list();
			return organizationList;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Boolean addOrganization(Organization organization) {

		boolean result = false;
		int i=0;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			i = (Integer) session.save(organization);
			if(i > 0) {
				result = true;
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public Organization getOrganizationByName(String organizationName){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Organization where organizationName= :organizationName"); 
			query.setParameter("organizationName", organizationName);
			Organization organization = null;
			@SuppressWarnings("unchecked")
			List<Organization> organizationList = query.list();
			if(! organizationList.isEmpty()){
				organization = (Organization) organizationList.get(0);
			}
			return organization;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Organization getOrganizationById(int organizationId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Organization where organizationId= :organizationId"); 
			query.setParameter("organizationId", organizationId);
			Organization organization = null;
			@SuppressWarnings("unchecked")
			List<Organization> organizationList = query.list();
			if(! organizationList.isEmpty()){
				organization = (Organization) organizationList.get(0);
			}
			return organization;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}	
}
