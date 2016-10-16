package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.dao.OrganizationDao;
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
	
	@Transactional
	public Organization getOrganizationByName(String organizationName){
		
		Organization organization = this.organizationDao.getOrganizationByName(organizationName);
		return organization;
	}
	
	@Transactional
	public Organization getOrganizationById(int organizationId){
		
		Organization organization = this.organizationDao.getOrganizationById(organizationId);
		return organization;
	}
	
	@Transactional
	public int addOrganization(Organization organization) {
		
		int result = 0;
		if(this.organizationDao.getOrganizationByName(organization.getOrganizationName()) != null) {
			result = 1;
		} else if(this.organizationDao.addOrganization(organization)) {
			result = 2;
		}
		return result;
	}
	
}
