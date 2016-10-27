package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Organization;
import com.ripperfit.model.Resource;

@Repository
public class ResourceDaoImpl implements ResourceDao {

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

	public boolean addResource(Resource resource) {
		
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.save(resource);
			return true;
		} catch(Exception ex) {
			throw ex;
		}finally{
			
		}
	}

	@SuppressWarnings("unchecked")
	public List<Resource> getAllResources() {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Resource.class).list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public Resource getResourceById(int resourceId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Resource where resourceId= :resourceId"); 
			query.setParameter("resourceId", resourceId);
			return (Resource)query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{

		}
	}

	public Resource getResourceByName(String resourceName){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Resource where resourceName= :resourceName"); 
			query.setParameter("resourceName", resourceName);
			return (Resource)query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public int getFinalApprovalLevel(int resourceId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("select resource.finalApprovalLevel from Resource"
					+ "resource where resourceId= :resourceId");
			query.setParameter("resourceId", resourceId);
			return (int) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public boolean updateResource(Resource resource) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(resource);
			return true;
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Resource> getAllResourcesInAnOrganization(Organization organization) {
		
		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(Resource.class).add( Restrictions.eq("organization",organization)).list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}
	
}