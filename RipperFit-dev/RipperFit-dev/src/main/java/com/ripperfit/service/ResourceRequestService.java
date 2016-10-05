package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.daoLayer.ResourceDAO;
import com.ripperfit.model.ResourceRequest;

@Service
public class ResourceRequestService {

	private ResourceDAO resourceDao;
	
	/**
	 * method to get the ResourceDAO object
	 * @return ResourceDAO object
	 */
	public ResourceDAO getResourceDao() {
		return resourceDao;
	}

	/**
	 * method to set the ResourceDAO object
	 * @param resourceDao object
	 */
	@Autowired(required=true)
	public void setResourceDao(ResourceDAO resourceDao) {
		this.resourceDao = resourceDao;
	}

	/**
	 * method to add the resource request
	 * @param request : Resource Request object
	 */
	@Transactional
	public void addResourceRequest(ResourceRequest request) {	
		this.resourceDao.addRequestDao(request);
	}
	
	/**
	 * method to delete resource request
	 * @param request : Resource Request object
	 */
	@Transactional
	public void deleteResourceRequest(int request_id) {
		this.resourceDao.deleteRequestDao(request_id);
	}
	
	/**
	 * method to view all resource requests made by an employee
	 * @param emp : Employee who wants to view his/her resource requests
	 * @return : list of resource requests
	 */
	@Transactional
	public List<ResourceRequest> viewResourceRequest(int employee_id) {
		return this.resourceDao.viewRequestDao(employee_id);
	}
	
	/**
	 * method to view all resource requests
	 * only for admin and helpdesk
	 * @return : list of resource requests
	 */
	@Transactional
	public List<ResourceRequest> viewAllResourceRequest() {
		return this.resourceDao.viewAllRequestsDao();
	}
}