package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

		Session session = this.sessionFactory.getCurrentSession();
		boolean result = false;
		int i=0;
		try {
			i = (Integer) session.save(resource);
			if(i > 0) {
				result = true;
			}
		} catch(Exception e) {

			e.printStackTrace();
		}
		return result;
	}


	public List<Resource> getAllResources() {

		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Resource> resourceList = session.createCriteria(Resource.class).list();
		return resourceList;
	}

	public boolean deleteResourceById(int resourceId){

		Session session = this.sessionFactory.getCurrentSession();
		String hql = "delete from Employee where resource_id= :resourceId";
		Query query = session.createQuery(hql);
		query.setParameter("resource_id", resourceId);
		query.executeUpdate();
		return true;
	}

	public boolean deleteResourceByName(String resourceName){

		Session session = this.sessionFactory.getCurrentSession();
		String hql = "delete from Employee where resource_name= :resourceName";
		Query query = session.createQuery(hql);
		query.setParameter("resource_name", resourceName);
		query.executeUpdate();
		return true;
	}

	public Resource getResourceById(int resourceId){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Resource where resource_id= :resourceId"); 
		query.setParameter("resource_id", resourceId);
		@SuppressWarnings("unchecked")
		List<Resource> resourceList = query.list();
		Resource resource = (Resource) resourceList.get(0);
		return resource;

	}

	public Resource getResourceByName(String resourceName){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Resource where resource_name= :resourceName"); 
		query.setParameter("resource_name", resourceName);
		@SuppressWarnings("unchecked")
		List<Resource> resourceList = query.list();
		Resource resource = (Resource) resourceList.get(0);
		return resource;
	}

}