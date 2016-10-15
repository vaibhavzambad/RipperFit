package com.ripperfit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ripperfit.dao.DesignationDao;
import com.ripperfit.dao.ResourceDao;
import com.ripperfit.dao.ResourceRequestDao;

@Service
public class RequestApprovalService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private DesignationDao designationDao;
	
	@Autowired
	private ResourceRequestDao resourceRequestDao;

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
	 * @return the designationDao
	 */
	public DesignationDao getDesignationDao() {
		return designationDao;
	}

	/**
	 * @param designationDao the designationDao to set
	 */
	public void setDesignationDao(DesignationDao designationDao) {
		this.designationDao = designationDao;
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
	
	/*public int forwardResourceRequestToUpperRoles(int resourceRequestId){
		
		int currentApprovalDesignationId = resourceRequestDao.getCurrentApprovalDesignationIdByRequestId(resourceRequestId);
		
	}*/

}
