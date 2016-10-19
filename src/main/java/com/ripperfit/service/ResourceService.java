package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.dao.ResourceDao;
import com.ripperfit.model.Department;
import com.ripperfit.model.Resource;

public class ResourceService {

	private ResourceDao resourceDao;

	/**
	 * @return the resourceDao
	 */
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	/**
	 * @param resourceDao the resourceDao to set
	 */
	@Autowired(required=true)
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Transactional
	public int addResource(Resource resource) {
		int result = 0;

		if(this.resourceDao.getResourceByName(resource.getResourceName()) != null) {
			result = 1;
		} else if(this.resourceDao.addResource(resource)) {
			result = 2;
		}
		return result;
	}

	@Transactional
	public List<Resource> getAllResources(){

		List<Resource> resourceList = this.resourceDao.getAllResources();
		return resourceList;
	}

	@Transactional
	public Resource getResourceByName(String resourceName){

		Resource resource = this.resourceDao.getResourceByName(resourceName);
		return resource;
	}

	@Transactional
	public Resource getResourceById(int resourceId){

		Resource resource = this.resourceDao.getResourceById(resourceId);
		return resource;
	}

	@Transactional
	public boolean deleteResourceByName(String resourceName){

		Resource resource = this.resourceDao.getResourceByName(resourceName);
		boolean result = false;
		if(resource != null){
			this.resourceDao.deleteResourceByName(resourceName);
			result = true;
		}
		return result;
	}

	@Transactional
	public boolean deleteResourceById(int resourceId){

		Resource resource = this.resourceDao.getResourceById(resourceId);
		boolean result = false;
		if(resource != null){
			this.resourceDao.deleteResourceById(resourceId);
			result = true;
		}
		return result;
	}
	
	@Transactional
	public void updateResource(Resource resource) {
		
		this.resourceDao.updateResource(resource);
	}
}