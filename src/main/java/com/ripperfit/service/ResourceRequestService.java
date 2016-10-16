package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ripperfit.dao.ResourceRequestDao;
import com.ripperfit.model.Employee;
import com.ripperfit.model.ResourceRequest;

@Service
public class ResourceRequestService {
	
	private ResourceRequestDao resourceRequestDao;
	
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
	 * method to add the resource request
	 * @param request : Resource Request object
	 */
	@Transactional
	public void addResourceRequest(ResourceRequest resourceRequest) {	
		
		this.resourceRequestDao.addRequest(resourceRequest);
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
}