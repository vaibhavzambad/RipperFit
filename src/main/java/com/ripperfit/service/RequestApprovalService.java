package com.ripperfit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ripperfit.dao.ResourceDao;
import com.ripperfit.dao.ResourceRequestDao;
import com.ripperfit.dao.UserDao;

@Service
public class RequestApprovalService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private ResourceRequestDao resourceRequestDao;
	
	@Autowired
	private UserDao userDao;

	/**
	 * @return the resourceDao
	 */
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	/**
	 * @param resourceDao the resourceDao to set
	 */
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	/**
	 * @return the resourceRequestDao
	 */
	public ResourceRequestDao getResourceRequestDao() {
		return resourceRequestDao;
	}

	/**
	 * @param resourceRequestDao the resourceRequestDao to set
	 */
	public void setResourceRequestDao(ResourceRequestDao resourceRequestDao) {
		this.resourceRequestDao = resourceRequestDao;
	}

	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public int getCurrentApprovalLevel(int requestId){
		
		int currentApprovalLevel = this.resourceRequestDao.getCurrentApprovalLevel(requestId);
		return currentApprovalLevel;
	}
	
	public int getFinalApprovalLevel(int resourceId){
		
		int finalApprovalLevel = this.resourceDao.getFinalApprovalLevel(resourceId);
		return finalApprovalLevel;
	}
	
	public void forwardRequest(int requestId,int resourceId){
		
		int currentApprovalLevel = getCurrentApprovalLevel(requestId);
		int finalApprovalLevel = getFinalApprovalLevel(resourceId);
		if(currentApprovalLevel >= finalApprovalLevel){
			// forward request to helpdesk
			// update approvee_request table
		}else{
			// forward request to reportToId
			// update approvee_request table
		}
	}
	
	
	

}
