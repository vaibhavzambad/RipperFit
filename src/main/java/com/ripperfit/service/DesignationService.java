package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.dao.DesignationDao;
import com.ripperfit.model.Department;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Organization;


@Transactional
public class DesignationService {

	@Autowired
	private DesignationDao designationDao;


	public DesignationDao getDesignationDao() {
		return designationDao;
	}

	public void setDesignationDao(DesignationDao designationDao) {
		this.designationDao = designationDao;
	}
	
	@Transactional
	public List<Designation> getAllDesignationsInAnOrganization(Organization organization)
	{
		List<Designation> designation=this.designationDao.getAllDesignationsInAnOrganization(organization);
		return designation;
	}
	
	@Transactional
	public List<Designation> getDesignationsInDepartment(Department department)
	{
		List<Designation> designation=this.designationDao.getDesignationsInDepartment(department);
		return designation;
	}

	@Transactional
	public int addDesignation(Designation designation , Organization organization) {

		int result = 0;
		
		if(this.designationDao.getDesignationBynameInOrganization(designation.getDesignationName(), organization) != null) {
			result = 1;
		} else if(this.designationDao.addDesignation(designation)) {
			result = 2;
		}

		return result;
	}

	@Transactional
	public Designation getDesignationByName(String designationName){

		Designation designation = this.designationDao.getDesignationByName(designationName);
		return designation;
	}

	@Transactional
	public Designation getDesignationById(int designationId){

		Designation designation = this.designationDao.getDesignationById(designationId);
		return designation;
	}

	@Transactional
	public void updateDesignationLevels(Designation designation) {
		
		List<Designation> designationList = this.designationDao.designationListAboveLevel(designation);
		for(Designation des : designationList) {

			des.setDesignationLevel(des.getDesignationLevel()+1);
		}
		this.designationDao.updateLevels(designationList);
	}
	@Transactional
	public Designation getDesignationInAnOrganization(String designationName,Organization organization)
	{
		
		Designation designation= this.designationDao.getDesignationBynameInOrganization(designationName, organization);
		return designation;
	}

	@Transactional
	public void updateDesignation(Designation designation) {
		
		this.designationDao.updateDesignation(designation);
	}
}