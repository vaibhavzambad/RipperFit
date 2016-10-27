package com.ripperfit.dao;

import java.util.List;

import com.ripperfit.model.Organization;

public interface OrganizationDao {
	
	public List<Organization> getAllOrganizations();

	public boolean addOrganization(Organization organization);

	public Organization getOrganizationByName(String organizationName);

	public Organization getOrganizationById(int organizationId);
}
