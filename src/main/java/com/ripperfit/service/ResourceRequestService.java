package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.dao.ApproveeRequestDao;
import com.ripperfit.dao.ResourceRequestDao;
import com.ripperfit.model.ApproveRequest;
import com.ripperfit.model.Employee;
import com.ripperfit.model.ResourceRequest;

@Service
public class ResourceRequestService {
	
	private ResourceRequestDao resourceRequestDao;
	
	private ApproveeRequestDao approveeRequestDao;
	
	/**
	 * method to get the ResourceDAO object
	 * @return ResourceDAO object
	 */
	public ResourceRequestDao getResourceDao() {
		return resourceRequestDao;
	}

	/**
	 * method to set the ResourceDAO object
	 * @param resourceDao object
	 */
	@Autowired(required=true)
	public void setResourceDao(ResourceRequestDao resourceDao) {
		
		this.resourceRequestDao = resourceDao;
	}
	
	

	/**
	 * @return the approveeRequestDao
	 */
	public ApproveeRequestDao getApproveeRequestDao() {
		return approveeRequestDao;
	}

	/**
	 * @param approveeRequestDao the approveeRequestDao to set
	 */
	@Autowired(required=true)
	public void setApproveeRequestDao(ApproveeRequestDao approveeRequestDao) {
		this.approveeRequestDao = approveeRequestDao;
	}

	/**
	 * method to add the resource request
	 * @param request : Resource Request object
	 */
	@Transactional
	public void addResourceRequest(ResourceRequest resourceRequest) {	
		
		this.resourceRequestDao.addRequest(resourceRequest);
		Employee employee = resourceRequest.getEmployee();
		Employee employeeToForward  =resourceRequest.getEmployee().getEmployee();
		ApproveRequest approveeRequest = new ApproveRequest();
		approveeRequest.setResourceRequest(resourceRequest);
		approveeRequest.setEmployee(employee);
		approveeRequest.setEmployeeToForward(employeeToForward);
		this.approveeRequestDao.addApproveeRequest(approveeRequest);
		
	}
	
	/**
	 * method to delete resource request
	 * @param request : Resource Request object
	 */
	@Transactional
	public boolean deleteResourceRequestById(int requestId) {
		
		ResourceRequest resourceRequest = this.resourceRequestDao.getResourceRequestById(requestId);
		boolean result = false;
		
		if(resourceRequest != null){
			this.resourceRequestDao.deleteRequestById(requestId);
			result = true;
		}
		
		return result;
	}
	
	/**
	 * method to view all resource requests made by an employee
	 * @param emp : Employee who wants to view his/her resource requests
	 * @return : list of resource requests
	 */
	@Transactional
	public List<ResourceRequest> getResourceRequestByEmployeeId(Employee employee) {
		
		List<ResourceRequest> resourceRequestListByEmployee = this.resourceRequestDao.getRequestByEmployeeId(employee);
		return resourceRequestListByEmployee;
	}
	
	@Transactional
	public ResourceRequest getResourceRequestById(int requestId){
		
		ResourceRequest resourceRequest = this.resourceRequestDao.getResourceRequestById(requestId);
		return resourceRequest;
	}
	
	
	
	/**
	 * method to view all resource requests
	 * only for admin and helpdesk
	 * @return : list of resource requests
	 */
	@Transactional
	public List<ResourceRequest> getAllResourceRequest() {
		
		List<ResourceRequest> resourceRequestList= this.resourceRequestDao.getAllRequests();
		return resourceRequestList;
	}
	
	@Transactional
	public int getCurrentApprovalLevelByRequestId(int requestId){
		
		int currentApprovalLevel = this.resourceRequestDao.getCurrentApprovalLevel(requestId);
		return currentApprovalLevel;
	}
}