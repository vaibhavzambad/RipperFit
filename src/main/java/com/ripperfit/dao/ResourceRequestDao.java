package com.ripperfit.dao;

import java.util.List;

import com.ripperfit.model.Employee;
import com.ripperfit.model.Organization;
import com.ripperfit.model.ResourceRequest;

public interface ResourceRequestDao {
	
	public boolean addRequest(ResourceRequest resourceRequest);

	public boolean deleteRequestById(int requestId);

	public List<ResourceRequest> getRequestByEmployeeId(Employee employee);

	public List<ResourceRequest> getAllRequests();

	public ResourceRequest getResourceRequestById(int requestId);

	public int getCurrentApprovalLevel(int requestId);

	public boolean updateCurrentApprovalLevel(int requestId,int updatedLevel);

	public boolean updateResourceRequest(ResourceRequest request);

	public List<ResourceRequest> getAllRequestsInAnOrganization(Organization organization);

	public List<ResourceRequest> getResourceRequestByStatus(String status , Organization organization);

}
