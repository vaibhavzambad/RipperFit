package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.dao.OrganizationDao;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Organization;

@Transactional
public class OrganizationService {
	
	@Autowired
	private OrganizationDao organizationDao;

	/**
	 * @return the organizationDao
	 */
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}

	/**
	 * @param organizationDao the organizationDao to set
	 */
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	
	@Transactional
	public List<Organization> getAllOrganizations()
	{
		List<Organization> organizationList=this.organizationDao.getAllOrganizations();
		return organizationList;
	}
}
