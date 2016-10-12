package com.ripperfit.daoLayer;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public void addRequestDao(ResourceRequest resourceRequest) {

		Session session = this.sessionFactory.getCurrentSession();
		session.save(resourceRequest);
	}

	/**
	 * method to delete resource request
	 * @param resourceRequest
	 */
	public int deleteRequestDao(int request_id) {

		Session session = this.sessionFactory.getCurrentSession();
		String hql = "DELETE FROM ResourceRequest WHERE request_id = :request_id";
		Query query = session.createQuery(hql);
		query.setParameter("request_id", request_id);
		int result = query.executeUpdate();
		return result;
	}

	/**
	 * method to view resource request by an employee
	 * @param emp : employee who wants to view his/hew resource requests
	 * @return : list of resource requests
	 */
	@SuppressWarnings("unchecked")
	public List<ResourceRequest> viewRequestDao(int employee_id) {

		Session session = this.sessionFactory.getCurrentSession();
		String hql = "FROM ResourceRequest WHERE UPPER(requestor_id)='"+employee_id+"'";
		List<ResourceRequest> requestList = session.createQuery(hql).list();
		return requestList;
	}

	/**
	 * method to view all resource requests made
	 * only for admin and helpdesk
	 * @return : list of all resource requests
	 */
	@SuppressWarnings("unchecked")
	public List<ResourceRequest> viewAllRequestsDao() {

		Session session = this.sessionFactory.getCurrentSession();
		List<ResourceRequest> requestList = session.createQuery("FROM ResourceRequest").list();
		return requestList;
	}

	public int getCurrentApprovalDesignationIdByRequestId(int requestId){

		Session session = this.sessionFactory.getCurrentSession();

		String hql = "SELECT current_approval_designation_id FROM ResourceRequest WHERE request_id = :requestId";
		int currentApprovalDesignationId = (int)session.createQuery(hql).setParameter("requestId", requestId).uniqueResult();
		return currentApprovalDesignationId;
		
	}

	public int getResourceIdByRequestId(int requestId){

		Session session = this.sessionFactory.getCurrentSession();

		String hql = "SELECT resource_id FROM ResourceRequest WHERE request_id = :requestId";
		int resourceId = (int)session.createQuery(hql).setParameter("requestId", requestId).uniqueResult();
		return resourceId;
		
	}
}