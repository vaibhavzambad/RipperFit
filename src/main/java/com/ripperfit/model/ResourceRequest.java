package com.ripperfit.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

public class ResourceRequest {
	
	@Id
	@Column(name="request_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int request_id;
	
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="resource_id")
	private List<Resource> resource;
	
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="requestor_id")
	private List<Employee> employee;
	
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="current_approval_designation_id")
	private List<Designation> designation;
	
	@Column(name="priority")
	private String priority;
	
	@Column(name="status")
	private String status;
	
	@Column(name="comments")
	private String comments;

	/**
	 * @return the request_id
	 */
	public int getRequest_id() {
		return request_id;
	}

	/**
	 * @param request_id the request_id to set
	 */
	public void setRequest_id(int request_id) {
		this.request_id = request_id;
	}


	/**
	 * @return the resource
	 */
	public List<Resource> getResource() {
		return resource;
	}

	/**
	 * @param resource the resource to set
	 */
	public void setResource(List<Resource> resource) {
		this.resource = resource;
	}

	/**
	 * @return the employee
	 */
	public List<Employee> getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	/**
	 * @return the designation
	 */
	public List<Designation> getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(List<Designation> designation) {
		this.designation = designation;
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
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
}