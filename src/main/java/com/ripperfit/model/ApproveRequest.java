package com.ripperfit.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="approve_request")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ApproveRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="request_id")
	private ResourceRequest resourceRequest;
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="approvee_id")
	private Employee employee;
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="forwardTo_id")
	private Employee employeeToForward;

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the resourceRequest
	 */
	public ResourceRequest getResourceRequest() {
		return resourceRequest;
	}

	/**
	 * @param resourceRequest the resourceRequest to set
	 */
	public void setResourceRequest(ResourceRequest resourceRequest) {
		this.resourceRequest = resourceRequest;
	}

	/**
	 * @return the employeeToForward
	 */
	public Employee getEmployeeToForward() {
		return employeeToForward;
	}

	/**
	 * @param employeeToForward the employeeToForward to set
	 */
	public void setEmployeeToForward(Employee employeeToForward) {
		this.employeeToForward = employeeToForward;
	}

}
