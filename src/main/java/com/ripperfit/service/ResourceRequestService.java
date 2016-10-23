package com.ripperfit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ripperfit.dao.ApproveeRequestDao;
import com.ripperfit.dao.DepartmentDao;
import com.ripperfit.dao.DesignationDao;
import com.ripperfit.dao.OrganizationDao;
import com.ripperfit.dao.ResourceDao;
import com.ripperfit.dao.ResourceRequestDao;
import com.ripperfit.dao.UserDao;
import com.ripperfit.model.ApproveRequest;
import com.ripperfit.model.Department;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Employee;
import com.ripperfit.model.Organization;
import com.ripperfit.model.Resource;
import com.ripperfit.model.ResourceRequest;

@Service
public class ResourceRequestService {

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
	
	@Autowired
	private OrganizationDao organizationDao;

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

	/**
	 * method to add the resource request
	 * @param request : Resource Request object
	 */
	@Transactional
	public void addResourceRequest(ResourceRequest resourceRequest) {	

		this.resourceRequestDao.addRequest(resourceRequest);
		Employee employee = resourceRequest.getEmployee();
		Resource resource = resourceRequest.getResource();
		Employee employeeToForward = null;
		if(employee.getDesignation().getDesignationLevel() <= resource.getFinalApprovalLevel()){
			Organization organization = employee.getOrganization();
			Department department = this.departmentDao.getHelpdeskDepartmentByOrganization(organization);
			Designation designation = this.designationDao.getDesignationByDepartment(department);
			employeeToForward = this.userDao.getEmployeeByDesignation(designation);
		}
		else {
			employeeToForward = resourceRequest.getEmployee().getEmployee();
		}
		ApproveRequest approveeRequest = new ApproveRequest();
		approveeRequest.setResourceRequest(resourceRequest);
		approveeRequest.setEmployee(employee);
		approveeRequest.setEmployeeToForward(employeeToForward);
		this.approveeRequestDao.addApproveeRequest(approveeRequest);

	}

	/**
	 * method to delete resource request
	 * @param request : Resource Request object
	 */
	@Transactional
	public boolean deleteResourceRequestById(int requestId) {

		ResourceRequest resourceRequest = this.resourceRequestDao.getResourceRequestById(requestId);
		boolean result = false;

		if(resourceRequest != null){
			this.resourceRequestDao.deleteRequestById(requestId);
			result = true;
		}

		return result;
	}

	/**
	 * method to view all resource requests made by an employee
	 * @param emp : Employee who wants to view his/her resource requests
	 * @return : list of resource requests
	 */
	@Transactional
	public List<ResourceRequest> getResourceRequestByEmployeeId(Employee employee) {

		List<ResourceRequest> resourceRequestListByEmployee = this.resourceRequestDao.getRequestByEmployeeId(employee);
		return resourceRequestListByEmployee;
	}

	@Transactional
	public ResourceRequest getResourceRequestById(int requestId){

		ResourceRequest resourceRequest = this.resourceRequestDao.getResourceRequestById(requestId);
		return resourceRequest;
	}

	/**
	 * method to view all resource requests
	 * only for admin and helpdesk
	 * @return : list of resource requests
	 */
	@Transactional
	public List<ResourceRequest> getAllResourceRequest() {

		List<ResourceRequest> resourceRequestList= this.resourceRequestDao.getAllRequests();
		return resourceRequestList;
	}

	@Transactional
	public int getCurrentApprovalLevelByRequestId(int requestId){

		int currentApprovalLevel = this.resourceRequestDao.getCurrentApprovalLevel(requestId);
		return currentApprovalLevel;
	}

	@Transactional
	public void updateResourceRequest(ResourceRequest request){

		this.resourceRequestDao.updateResourceRequest(request);
	}

	/**
	 * here both the admin and helpdesk are obtained by there organization 
	 * requests for both are obtained and sent to helpdesk
	 * 
	 * @param organization
	 * @return all request of organization which are for helpdesk 
	 */
	@Transactional
	public List<ResourceRequest> getAllRequestsInAnOrganizationForHelpdesk(Organization organization){
		
		Department helpdeskDepartment = this.departmentDao.getHelpdeskDepartmentByOrganization(organization);
		Designation helpdeskDesignation = this.designationDao.getDesignationByDepartment(helpdeskDepartment);
		Employee helpDesk = this.userDao.getEmployeeByDesignation(helpdeskDesignation);
		
		Department adminDepartment = this.departmentDao.getAdminDepartmentByOrganization(organization);
		Designation adminDesignation = this.designationDao.getDesignationByDepartment(adminDepartment);
		Employee admin = this.userDao.getEmployeeByDesignation(adminDesignation);
		
		List<ResourceRequest> requestForHelpdesk = this.approveeRequestDao.getResourceRequestListByForwardToId(admin);
		List<ResourceRequest> requestForAdmin = this.approveeRequestDao.getResourceRequestListByForwardToId(helpDesk);
		
		requestForHelpdesk.removeAll(requestForAdmin);
		requestForHelpdesk.addAll(requestForAdmin);
		
		return requestForHelpdesk;
	}
	
	
	@Transactional
	public List<ResourceRequest> getResourceRequestByStatus(String status , String organizationName){
		
		Organization organization = organizationDao.getOrganizationByName(organizationName);
		List<ResourceRequest> resourceRequests = this.resourceRequestDao.getResourceRequestByStatus(status,organization);
		return resourceRequests;
	}
}