package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.dao.ApproveeRequestDao;
import com.ripperfit.model.ApproveRequest;
import com.ripperfit.model.Employee;
import com.ripperfit.model.ResourceRequest;

@Service
public class ApproveeRequestService {

	@Autowired
	private ApproveeRequestDao approveeRequestDao;

	/**
	 * @return the approveeRequestDao
	 */
	public ApproveeRequestDao getApproveeRequestDao() {
		return approveeRequestDao;
	}

	/**
	 * @param approveeRequestDao the approveeRequestDao to set
	 */
	public void setApproveeRequestDao(ApproveeRequestDao approveeRequestDao) {
		this.approveeRequestDao = approveeRequestDao;
	}

	@Transactional
	public List<ResourceRequest> getResourceRequestListByForwardToId(Employee employeeToForward){
		
		List<ResourceRequest> resourceRequestList = this.approveeRequestDao.getResourceRequestListByForwardToId(employeeToForward);
		return resourceRequestList;
	}
	
	@Transactional
	public ApproveRequest getApproveeRequestByApproveeId(Employee employee){
		
		ApproveRequest approveeRequest = this.approveeRequestDao.getApproveeRequestByApproveeId(employee);
		return approveeRequest;
	}

}
