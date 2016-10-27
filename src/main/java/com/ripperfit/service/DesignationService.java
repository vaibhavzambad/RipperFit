package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.CustomExceptions.DesignationAlreadyPresentException;
import com.ripperfit.CustomExceptions.DesignationsDoesNotExistsException;
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
	public List<Designation> getAllDesignationsInAnOrganization(Organization organization) throws Exception
	{	
		try{
			List<Designation> designationsList = this.designationDao.getAllDesignationsInAnOrganization(organization);
			if(designationsList.isEmpty()){
				throw new DesignationsDoesNotExistsException("Designations does not exists");
			}
			return designationsList;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public List<Designation> getDesignationsInDepartment(Department department) throws Exception
	{	
		try{
			List<Designation> designationsList = this.designationDao.getDesignationsInDepartment(department);
			if(designationsList.isEmpty()){
				throw new DesignationsDoesNotExistsException("Designations does not exists");
			}
			return designationsList;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public boolean addDesignation(Designation designation , Organization organization) throws Exception {

		try{
			if(this.designationDao.getDesignationBynameInOrganization(designation.getDesignationName(), organization) != null) {
				throw new DesignationAlreadyPresentException("Designation Already Present");
			} 
			this.designationDao.addDesignation(designation);
		}catch(Exception ex){
			throw ex;
		}
		return true;
	}

	@Transactional
	public Designation getDesignationByName(String designationName) throws Exception{

		try{
			Designation designation = this.designationDao.getDesignationByName(designationName);
			if(designation == null){
				throw new DesignationsDoesNotExistsException("Designations does not exists");
			}
			return designation;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public Designation getDesignationById(int designationId) throws Exception{

		try{
			Designation designation = this.designationDao.getDesignationById(designationId);
			if(designation == null){
				throw new DesignationsDoesNotExistsException("Designations does not exists");
			}
			return designation;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public boolean updateDesignationLevels(Designation designation) throws Exception {

		try{
			List<Designation> designationList = this.designationDao.designationListAboveLevel(designation);
			if(designationList.isEmpty()){
				throw new DesignationsDoesNotExistsException("Designations does not exists");
			}
			for(Designation des : designationList) {

				des.setDesignationLevel(des.getDesignationLevel()+1);
			}
			return this.designationDao.updateLevels(designationList);
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public Designation getDesignationInAnOrganization(String designationName,Organization organization) throws Exception{

		try{
			Designation designation = this.designationDao.getDesignationBynameInOrganization(designationName, organization);
			if(designation == null){
				throw new DesignationsDoesNotExistsException("Designations does not exists");
			}
			return designation;
		}catch(Exception ex){
			throw ex;
		}
	}

	@Transactional
	public boolean updateDesignation(Designation designation) throws Exception {

		try{
			if(this.designationDao.getDesignationById(designation.getDesignationId()) == null){
				throw new DesignationsDoesNotExistsException("Designations does not exists");
			}
			return this.designationDao.updateDesignation(designation);
		}catch(Exception ex){
			throw ex;
		}
	}
}