package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.CustomExceptions.ResourceAlreadyExistsException;
import com.ripperfit.CustomExceptions.ResourceNotExistsException;
import com.ripperfit.dao.ResourceDao;
import com.ripperfit.model.Organization;
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
	public boolean addResource(Resource resource) throws Exception {

		try{
			if(this.resourceDao.getResourceByName(resource.getResourceName()) != null) {
				throw new ResourceAlreadyExistsException("Resource Already Exist");
			} 
			return this.resourceDao.addResource(resource);
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public List<Resource> getAllResources() throws Exception{

		try{
			List<Resource> resourceList = this.resourceDao.getAllResources();
			if(resourceList.isEmpty()){
				throw new ResourceNotExistsException("Resource does not exists");
			}
			return resourceList;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public Resource getResourceByName(String resourceName) throws Exception{

		try{
			Resource resource = this.resourceDao.getResourceByName(resourceName);
			if(resource == null){
				throw new ResourceNotExistsException("Resource does not exists");
			}
			return resource;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public Resource getResourceById(int resourceId) throws Exception{

		try{
			Resource resource = this.resourceDao.getResourceById(resourceId);
			if(resource == null){
				throw new ResourceNotExistsException("Resource does not exists");
			}
			return resource;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public boolean updateResource(Resource resource) throws Exception {

		try{
			if(this.resourceDao.getResourceById(resource.getResourceId()) == null){
				throw new ResourceNotExistsException("Resource does not exists");
			}
			return this.resourceDao.updateResource(resource);
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public List<Resource> getAllResourcesInAnOrganization(Organization organization) throws Exception{

		try{
			
			List<Resource> resourceList = this.resourceDao.getAllResourcesInAnOrganization(organization);
			if(resourceList.isEmpty()){
				throw new ResourceNotExistsException("Resource does not exists");
			}
			return resourceList;
		}catch(Exception ex){
			throw ex;
		}
	}
}