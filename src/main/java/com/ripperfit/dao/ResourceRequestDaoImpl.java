package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.Employee;
import com.ripperfit.model.Organization;
import com.ripperfit.model.ResourceRequest;

@Repository
public class ResourceRequestDaoImpl implements ResourceRequestDao {

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
	 * method to add resource request
	 * @param resourceRequest
	 */
	public boolean addRequest(ResourceRequest resourceRequest) {

		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.save(resourceRequest);
			return true;
		} catch(Exception ex) {
			throw ex;
		}finally{

		}
	}

	/**
	 * method to delete resource request
	 * @param resourceRequest
	 */
	public boolean deleteRequestById(int requestId) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			String hql = "DELETE FROM ResourceRequest WHERE requestId = :requestId";
			session.createQuery(hql).setParameter("requestId", requestId).executeUpdate();
			return true;
		}catch(Exception ex){
			throw ex;
		}finally{

		}
	}

	/**
	 * method to view resource request by an employee
	 * @param emp : employee who wants to view his/hew resource requests
	 * @return : list of resource requests
	 */
	@SuppressWarnings("unchecked")
	public List<ResourceRequest> getRequestByEmployeeId(Employee employee) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return (List<ResourceRequest>)session.createCriteria(ResourceRequest.class)
					.add(Restrictions.eq("employee.employeeId",employee.getEmployeeId())).list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	/**
	 * method to view all resource requests made
	 * only for admin and helpdesk
	 * @return : list of all resource requests
	 */
	@SuppressWarnings("unchecked")
	public List<ResourceRequest> getAllRequests() {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createQuery("FROM ResourceRequest").list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public ResourceRequest getResourceRequestById(int requestId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from ResourceRequest where requestId= :requestId"); 
			query.setParameter("requestId", requestId);
			ResourceRequest resourceRequest = (ResourceRequest) query.uniqueResult();
			return resourceRequest;
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}


	public int getCurrentApprovalLevel(int requestId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("select resourceRequest.currentApprovalLevel from ResourceRequest"
					+ "resourceRequest where requestId= :requestId");
			query.setParameter("requestId", requestId);
			return (int) query.uniqueResult();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public boolean updateCurrentApprovalLevel(int requestId,int updatedLevel){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query q = session.createQuery("update ResourceRequest set currentApprovalLevel= :updatedCurrentApprovalLevel"
					+ " where requestId= :requestId");
			q.setParameter("updatedCurrentApprovalLevel", updatedLevel);
			q.setParameter("requestId", requestId);
			q.executeUpdate();
			return true;
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	public boolean updateResourceRequest(ResourceRequest request) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(request);
			return true;
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}

	@SuppressWarnings("unchecked")
	public List<ResourceRequest> getAllRequestsInAnOrganization(Organization organization) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			return session.createCriteria(ResourceRequest.class).add(Restrictions.eq("organization",organization)).list();
		}catch(Exception ex){
			throw ex;
		}finally{

		}
	}

	@SuppressWarnings("unchecked")
	public List<ResourceRequest> getResourceRequestByStatus(String status , Organization organization){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from ResourceRequest where status= :status and organization= :organization"); 
			query.setParameter("status", status);
			query.setParameter("organization", organization);
			return query.list();
		}catch(Exception ex){
			throw ex;
		}finally{
			
		}
	}
}