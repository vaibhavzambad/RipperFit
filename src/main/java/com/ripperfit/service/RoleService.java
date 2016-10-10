package com.ripperfit.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.ripperfit.daoLayer.RoleDao;
import com.ripperfit.model.Designation;

@Transactional
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	@Transactional
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
	
	Designation getDesignationByRole(String designation )
	{
		Designation desig=getDesignationByRole(designation);
		return desig;
		
	}
}
