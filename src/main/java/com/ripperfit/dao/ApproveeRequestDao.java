package com.ripperfit.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ripperfit.model.ApproveRequest;
import com.ripperfit.model.Employee;
import com.ripperfit.model.ResourceRequest;

@Repository
public class ApproveeRequestDao {

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

	public boolean addApproveeRequest(ApproveRequest approveRequest) {

		Session session = this.sessionFactory.getCurrentSession();
		boolean result = false;
		int i=0;
		try {
			i = (Integer) session.save(approveRequest);
			if(i > 0) {
				result = true;

			}
		} catch(Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	public boolean updateApproveeRequestByRequestId(ApproveRequest approveeRequest){
		
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery("update ApproveRequest set employee= :employee,employeeToForward= :employeeToForward"
				+ " where resourceRequest= :resourceRequest");

		q.setParameter("employee", approveeRequest.getEmployee());
		q.setParameter("employeeToForward", approveeRequest.getEmployeeToForward());
		q.setParameter("resourceRequest", approveeRequest.getResourceRequest());
		q.executeUpdate();
		return true;

	}

	public List<ResourceRequest> getResourceRequestListByForwardToId(Employee employeeToForward){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ApproveRequest where employeeToForward= :employeeToForward"); 
		query.setParameter("employeeToForward", employeeToForward);
		@SuppressWarnings("unchecked")
		List<ResourceRequest> resourceRequestList = query.list();
		return resourceRequestList;
	}
	
	

	public ApproveRequest getApproveeRequestByApproveeId(Employee employee){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ApproveRequest where employee= :employee"); 
		query.setParameter("employee", employee);
		ApproveRequest approveRequest = (ApproveRequest) query.uniqueResult();
		return approveRequest;

	}

	public ApproveRequest getApproveeRequestByRequestId(ResourceRequest resourceRequest){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ApproveRequest where resourceRequest= :resourceRequest"); 
		query.setParameter("resourceRequest", resourceRequest);
		ApproveRequest approveRequest = (ApproveRequest) query.uniqueResult();
		return approveRequest;

	}
	
	public Employee getApproveeEmployeeByRequestId(ResourceRequest resourceRequest){

		Session session = this.sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ApproveRequest where resourceRequest= :resourceRequest"); 
		query.setParameter("resourceRequest", resourceRequest);
		ApproveRequest approveeRequest = (ApproveRequest) query.uniqueResult(); 
		Employee employee = approveeRequest.getEmployee();
		return employee;

	}
}
