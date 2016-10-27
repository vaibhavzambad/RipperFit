package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.dao.ApproveeRequestDao;
import com.ripperfit.model.ApproveRequest;
import com.ripperfit.model.Employee;
import com.ripperfit.model.ResourceRequest;
import com.ripperfit.util.AppException;
import com.ripperfit.util.ExceptionConstants;

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
	public List<ResourceRequest> getResourceRequestListByForwardToId(Employee employeeToForward) throws Exception{
		try{
			List<ResourceRequest> resourceRequestList = this.approveeRequestDao.getResourceRequestListByForwardToId(employeeToForward);
			if(resourceRequestList.isEmpty()){
				throw new AppException(ExceptionConstants.REQUEST_NOT_EXIST);
			}
			return resourceRequestList;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public ApproveRequest getApproveeRequestByApproveeId(Employee employee){
		try{
			return this.approveeRequestDao.getApproveeRequestByApproveeId(employee);
		}catch(Exception ex){
			throw ex;
		}
	}
}