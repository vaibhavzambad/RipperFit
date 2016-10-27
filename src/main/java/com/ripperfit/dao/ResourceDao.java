package com.ripperfit.dao;

import java.util.List;

import com.ripperfit.model.Organization;
import com.ripperfit.model.Resource;


public interface ResourceDao {
	
	public boolean addResource(Resource resource);

	public List<Resource> getAllResources();

	public Resource getResourceById(int resourceId);

	public Resource getResourceByName(String resourceName);
	
	public int getFinalApprovalLevel(int resourceId);
	
	public boolean updateResource(Resource resource);
	
	public List<Resource> getAllResourcesInAnOrganization(Organization organization);

}
