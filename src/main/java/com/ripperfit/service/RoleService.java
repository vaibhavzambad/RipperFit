package com.ripperfit.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.dao.DesignationDao;
import com.ripperfit.model.Designation;


@Transactional
public class RoleService {

	@Autowired
	private DesignationDao designationDao;
	

	public DesignationDao getDesignationDao() {
		return designationDao;
	}

	public void setDesignationDao(DesignationDao designationDao) {
		this.designationDao = designationDao;
	}
	
	/*@Transactional
	Boolean addRole(Designation designation)
	{
		
		Boolean flag=this.roleDao.addRole(designation);
		return flag;
	}
	@Transactional
	Boolean deleteRole(String designation)
	{
		Boolean flag=this.roleDao.deleteRole(designation);
		return flag;
	}
	@Transactional
	Boolean updateRoleByName(String designation,String updatedDesignation)
	{
		Boolean flag=this.roleDao.updateRoleByName(designation,updatedDesignation);
		return flag;
	}
	@Transactional
	Designation getDesignationByRole(String designation )
	{
		Designation desig=getDesignationByRole(designation);
		return desig;
		
	}*/
	
	@Transactional
	public
	
	List<Designation> viewAllRoles()
	{
		List<Designation> des=this.designationDao.viewAllRoles();
		return des;
	}
	
}
