package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.dao.DesignationDao;
import com.ripperfit.model.Designation;


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
	public List<Designation> getAllDesignation()
	{
		List<Designation> designation=this.designationDao.viewAllRoles();
		return designation;
	}

	@Transactional
	public int addDesignation(Designation designation) {

		int result = 2;
		
		this.designationDao.addDesignation(designation);
		/*if(this.designationDao.getDesignationByName(designation.getDesignationName()) != null) {
			result = 1;
		} else if(this.designationDao.addDesignation(designation)) {
			result = 2;
		}*/

		return result;
	}

	@Transactional
	public boolean deleteDesignationByName(String designationName){

		Designation designation = this.getDesignationByName(designationName);
		boolean result = false;
		if(designation != null){
			this.designationDao.deleteDesignationByName(designationName);
			result = true;
		}
		return result;
	}

	@Transactional
	public boolean deleteDesignationById(int designationId){
		
		Designation designation = this.getDesignationById(designationId);
		boolean result = false;
		if(designation != null){
			this.designationDao.deleteDesignationById(designationId);
			result = true;
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
	public void updateDesignationLevels(int designationLevel) {
		
		List<Designation> designationList = this.designationDao.designationListAboveLevel(designationLevel);
		for(Designation des : designationList) {

			des.setDesignationLevel(des.getDesignationLevel()+1);
		}
		this.designationDao.updateLevels(designationList);
	}
}