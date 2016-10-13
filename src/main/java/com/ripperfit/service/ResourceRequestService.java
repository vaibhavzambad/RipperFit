package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.dao.ResourceRequestDao;
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
	public void addResourceRequest(ResourceRequest request) {	
		this.resourceRequestDao.addRequestDao(request);
	}
	
	/**
	 * method to delete resource request
	 * @param request : Resource Request object
	 */
	@Transactional
	public int deleteResourceRequest(int request_id) {
		int result = this.resourceRequestDao.deleteRequestDao(request_id);
		return result;
	}
	
	/**
	 * method to view all resource requests made by an employee
	 * @param emp : Employee who wants to view his/her resource requests
	 * @return : list of resource requests
	 */
	@Transactional
	public List<ResourceRequest> viewResourceRequest(int employee_id) {
		return this.resourceRequestDao.viewRequestDao(employee_id);
	}
	
	/**
	 * method to view all resource requests
	 * only for admin and helpdesk
	 * @return : list of resource requests
	 */
	@Transactional
	public List<ResourceRequest> viewAllResourceRequest() {
		return this.resourceRequestDao.viewAllRequestsDao();
	}
}