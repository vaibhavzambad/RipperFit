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

	public boolean addResource(Resource resource) {

		boolean result = false;
		int i=0;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			i = (Integer) session.save(resource);
			if(i > 0) {
				result = true;
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<Resource> getAllResources() {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Resource> resourceList = session.createCriteria(Resource.class).list();
			return resourceList;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Resource getResourceById(int resourceId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Resource where resourceId= :resourceId"); 
			query.setParameter("resourceId", resourceId);
			@SuppressWarnings("unchecked")
			List<Resource> resourceList = query.list();
			Resource resource = (Resource) resourceList.get(0);
			return resource;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Resource getResourceByName(String resourceName){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Resource where resourceName= :resourceName"); 
			query.setParameter("resourceName", resourceName);
			@SuppressWarnings("unchecked")
			List<Resource> resourceList = query.list();
			if(resourceList.size() > 0) {
				Resource resource = (Resource) resourceList.get(0);
				return resource;
			}
			return null;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public int getFinalApprovalLevel(int resourceId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("select resource.finalApprovalLevel from Resource"
					+ "resource where resourceId= :resourceId");

			query.setParameter("resourceId", resourceId);
			int finalApprovalLevel = (int) query.uniqueResult();
			return finalApprovalLevel;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}

	}

	public void updateResource(Resource resource) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(resource);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public List<Resource> getAllResourcesInAnOrganization(Organization organization) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<Resource> resource = session.createCriteria(Resource.class).add( Restrictions.eq("organization",organization)).list();
			return resource;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}