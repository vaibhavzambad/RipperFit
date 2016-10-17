package com.ripperfit.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="resource_request")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ResourceRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="request_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int requestId;
	
	/*@ManyToOne(optional=false,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="organization_id")
	private Organization organization;*/
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="resource_id")
	private Resource resource;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="requestor_id")
	private Employee employee;
	
	@Column(name="current_approval_level")
	private int currentApprovalLevel;
	
	@Column(name="priority")
	private String priority;
	
	@Column(name="status")
	private String status;
	
	@Column(name="message")
	private String message;
	
	@Column(name="request_date")
	private Date requestDate;
	
	

	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}

	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	/**
	 * @return the organization
	 */
	/*public Organization getOrganization() {
		return organization;
	}*/

	/**
	 * @param organization the organization to set
	 */
	/*public void setOrganization(Organization organization) {
		this.organization = organization;
	}*/

	/**
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(Resource resource) {
		this.resource = resource;
	}

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
	 * @return the currentApprovalLevel
	 */
	public int getCurrentApprovalLevel() {
		return currentApprovalLevel;
	}

	/**
	 * @param currentApprovalLevel the currentApprovalLevel to set
	 */
	public void setCurrentApprovalLevel(int currentApprovalLevel) {
		this.currentApprovalLevel = currentApprovalLevel;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the requestDate
	 */
	public Date getRequestDate() {
		return requestDate;
	}

	/**
	 * @param requestDate the requestDate to set
	 */
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	
	
}