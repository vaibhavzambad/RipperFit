package com.ripperfit.dao;

import java.util.List;

import com.ripperfit.model.Department;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Organization;

public interface DesignationDao {
	
	public List<Designation> getAllDesignationsInAnOrganization(Organization organization);
	
	public List<Designation> getDesignationsInDepartment(Department department);
	
	public boolean addDesignation(Designation designation);
	
	public Designation getDesignationById(int designationId);
	
	public Designation getDesignationByName(String designationName);
	
	public Designation getDesignationByDepartment(Department department);
	
	public boolean updateLevels(List<Designation> designationList);
	
	public List<Designation> designationListAboveLevel(Designation designation);
	
	public Designation getDesignationBynameInOrganization(String designationName , Organization organization);
	
	public boolean updateDesignation(Designation designation);

}
