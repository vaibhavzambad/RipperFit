package com.ripperfit.dao;

import java.util.List;

import com.ripperfit.model.ApproveRequest;
import com.ripperfit.model.Employee;
import com.ripperfit.model.ResourceRequest;

public interface ApproveeRequestDao {
	
	public boolean addApproveeRequest(ApproveRequest approveRequest);
	
	public boolean updateApproveeRequestByRequestId(ApproveRequest approveeRequest);
	
	public List<ResourceRequest> getResourceRequestListByForwardToId(Employee employeeToForward);
	
	public ApproveRequest getApproveeRequestByApproveeId(Employee employee);
	
	public ApproveRequest getApproveeRequestByRequestId(ResourceRequest resourceRequest);
	
	public Employee getApproveeEmployeeByRequestId(ResourceRequest resourceRequest);
	
}
