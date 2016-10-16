package com.ripperfit.dao;

import java.util.List;

import javax.transaction.Transactional;

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
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Organization> organizationList = session.createCriteria(Organization.class).list();
		System.out.println("organi: "+organizationList.size());
		return organizationList;
	}

	@Transactional
	public Boolean addOrganization(Organization organization) {
		
		Session session = this.sessionFactory.getCurrentSession();
		boolean result = false;
		int i=0;
		try {
			i = (Integer) session.save(organization);
			if(i > 0) {
				result = true;
			}
		} catch(Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	@Transactional
	public Organization getOrganizationByName(String organizationName){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Organization where organizationName= :organizationName"); 
		query.setParameter("organizationName", organizationName);

		@SuppressWarnings("unchecked")
		List<Organization> organizationList = query.list();
		Organization organization = (Organization) organizationList.get(0);
		return organization;
	}

	@Transactional
	public Organization getOrganizationById(int organizationId){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Organization where organizationId= :organizationId"); 
		query.setParameter("organizationId", organizationId);

		@SuppressWarnings("unchecked")
		List<Organization> organizationList = query.list();
		Organization organization = (Organization) organizationList.get(0);
		return organization;
		
	}
}
