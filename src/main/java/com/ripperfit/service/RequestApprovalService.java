package com.ripperfit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.dao.ApproveeRequestDao;
import com.ripperfit.dao.DepartmentDao;
import com.ripperfit.dao.DesignationDao;
import com.ripperfit.dao.ResourceDao;
import com.ripperfit.dao.ResourceRequestDao;
import com.ripperfit.dao.UserDao;
import com.ripperfit.model.ApproveRequest;
import com.ripperfit.model.Department;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Employee;
import com.ripperfit.model.Organization;
import com.ripperfit.model.ResourceRequest;

@Service
public class RequestApprovalService {

	@Autowired
	private ResourceDao resourceDao;

	@Autowired
	private ResourceRequestDao resourceRequestDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ApproveeRequestDao approveeRequestDao;
	
	@Autowired
	private DesignationDao designationDao;
	
	@Autowired
	private DepartmentDao departmentDao;

	/**
	 * @return the resourceDao
	 */
	public ResourceDao getResourceDao() {
		return resourceDao;
	}

	/**
	 * @param resourceDao the resourceDao to set
	 */
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	/**
	 * @return the resourceRequestDao
	 */
	public ResourceRequestDao getResourceRequestDao() {
		return resourceRequestDao;
	}

	/**
	 * @param resourceRequestDao the resourceRequestDao to set
	 */
	public void setResourceRequestDao(ResourceRequestDao resourceRequestDao) {
		this.resourceRequestDao = resourceRequestDao;
	}

	/**
	 * @return the userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}

	/**
	 * @param userDao the userDao to set
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	/**
	 * @return the approveeRequestDao
	 */
	public ApproveeRequestDao getApproveeRequestDao() {
		return approveeRequestDao;
	}

	/**
	 * @param approveeRequestDao the approveeRequestDao to set
	 */
	public void setApproveeRequestDao(ApproveeRequestDao approveeRequestDao) {
		this.approveeRequestDao = approveeRequestDao;
	}
	
	

	/**
	 * @return the designationDao
	 */
	public DesignationDao getDesignationDao() {
		return designationDao;
	}

	/**
	 * @param designationDao the designationDao to set
	 */
	public void setDesignationDao(DesignationDao designationDao) {
		this.designationDao = designationDao;
	}
	

	/**
	 * @return the departmentDao
	 */
	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	/**
	 * @param departmentDao the departmentDao to set
	 */
	public void setDepartmentDao(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}

	public int getFinalApprovalLevel(int resourceId){

		int finalApprovalLevel = this.resourceDao.getFinalApprovalLevel(resourceId);
		return finalApprovalLevel;
	}

	@Transactional
	public boolean forwardRequest(int requestId){

		ResourceRequest resourceRequest  = this.resourceRequestDao.getResourceRequestById(requestId);
		boolean result = false;
		if(resourceRequest != null){
			int finalApprovalLevel = resourceRequest.getResource().getFinalApprovalLevel();
			Employee employee = resourceRequest.getEmployee();
			Organization organization = employee.getOrganization();
			Department department = this.departmentDao.getHelpdeskDepartmentByOrganization(organization);
			Designation designation = this.designationDao.getDesignationByDepartment(department);
			Employee helpDeskEmployee = this.userDao.getEmployeeByDesignation(designation);
			ApproveRequest approveRequest = this.approveeRequestDao.getApproveeRequestByRequestId(resourceRequest);
			if(approveRequest != null){
				Employee forwardToEmployee = approveRequest.getEmployeeToForward();
				
				if(forwardToEmployee != null){
					approveRequest.setEmployee(forwardToEmployee);
					approveRequest.setResourceRequest(resourceRequest);
					approveRequest.setEmployeeToForward(forwardToEmployee.getEmployee());
					this.approveeRequestDao.updateApproveeRequestByRequestId(approveRequest);
					int updatedLevel = forwardToEmployee.getDesignation().getDesignationLevel();
					boolean temp = this.resourceRequestDao.updateCurrentApprovalLevel(resourceRequest.getRequestId(), updatedLevel);
					if(temp){
						if(updatedLevel >= finalApprovalLevel){
							approveRequest.setEmployee(forwardToEmployee);
							approveRequest.setResourceRequest(resourceRequest);
							approveRequest.setEmployeeToForward(helpDeskEmployee);
							this.approveeRequestDao.updateApproveeRequestByRequestId(approveRequest);
						}
					}
					result = true;
				}
				else{
					Employee currentApprovee = this.approveeRequestDao.getApproveeEmployeeByRequestId(resourceRequest);
					approveRequest.setEmployee(currentApprovee);
					approveRequest.setResourceRequest(resourceRequest);
					approveRequest.setEmployeeToForward(helpDeskEmployee);
					this.approveeRequestDao.updateApproveeRequestByRequestId(approveRequest);
				}
			}
		}
		return result;
	}
	
	

}
