package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.CustomExceptions.OrganizationAlreadyPresentException;
import com.ripperfit.CustomExceptions.OrganizationDoesNotExistsException;
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
	public List<Organization> getAllOrganizations() throws Exception{

		try{
			List<Organization> organizationsList = this.organizationDao.getAllOrganizations();
			if(organizationsList.isEmpty()){
				throw new OrganizationDoesNotExistsException("Organization does not exists");
			}
			return organizationsList;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public Organization getOrganizationByName(String organizationName) throws Exception{

		try{
			Organization organization = this.organizationDao.getOrganizationByName(organizationName);
			if(organization == null){
				throw new OrganizationDoesNotExistsException("Organization does not exists");
			}
			return organization;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public Organization getOrganizationById(int organizationId) throws Exception{

		try{
			Organization organization = this.organizationDao.getOrganizationById(organizationId);
			if(organization == null){
				throw new OrganizationDoesNotExistsException("Organization does not exists");
			}
			return organization;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public boolean addOrganization(Organization organization) throws Exception {

		try{
			if(this.organizationDao.getOrganizationByName(organization.getOrganizationName()) != null) {
				throw new OrganizationAlreadyPresentException("Organization Already Present");
			}
			return this.organizationDao.addOrganization(organization);
		}catch(Exception ex){
			throw ex;
		}
	}
}