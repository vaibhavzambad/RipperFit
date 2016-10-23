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
public class ResourceRequestDao {

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

		boolean result = false;
		int i=0;
		try {
			Session session = this.sessionFactory.getCurrentSession();
			i = (Integer) session.save(resourceRequest);
			if(i > 0) {
				result = true;
			}
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
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
			Query query = session.createQuery(hql);
			query.setParameter("requestId", requestId);
			query.executeUpdate();
			return true;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
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
			List<ResourceRequest> requestList = (List<ResourceRequest>)session.createCriteria(ResourceRequest.class)
					.add(Restrictions.eq("employee.employeeId",employee.getEmployeeId())).list();
			return requestList;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
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
			List<ResourceRequest> requestList = session.createQuery("FROM ResourceRequest").list();
			return requestList;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public ResourceRequest getResourceRequestById(int requestId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from ResourceRequest where requestId= :requestId"); 
			query.setParameter("requestId", requestId);
			@SuppressWarnings("unchecked")
			List<ResourceRequest> requestList = query.list();
			ResourceRequest resourceRequest = null;
			if(!requestList.isEmpty()){
				resourceRequest = (ResourceRequest) requestList.get(0);
			}
			return resourceRequest;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}


	public int getCurrentApprovalLevel(int requestId){

		try{
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("select resourceRequest.currentApprovalLevel from ResourceRequest"
					+ "resourceRequest where requestId= :requestId");

			query.setParameter("requestId", requestId);
			int currentApprovalLevel = (int) query.uniqueResult();
			return currentApprovalLevel;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
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
			ex.printStackTrace();
			throw ex;
		}
	}

	public void updateResourceRequest(ResourceRequest request) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.update(request);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public List<ResourceRequest> getAllRequestsInAnOrganization(Organization organization) {

		try{
			Session session = this.sessionFactory.getCurrentSession();
			@SuppressWarnings("unchecked")
			List<ResourceRequest> request = session.createCriteria(ResourceRequest.class).add(Restrictions.eq("organization",organization)).list();
			return request;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public List<ResourceRequest> getResourceRequestByStatus(String status , Organization organization){

		try{
			
			Session session = this.sessionFactory.getCurrentSession();
			Query query = session.createQuery("from ResourceRequest where status= :status and organization= :organization"); 
			query.setParameter("status", status);
			query.setParameter("organization", organization);
			@SuppressWarnings("unchecked")
			List<ResourceRequest> requestList = query.list();
			return requestList;
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
}