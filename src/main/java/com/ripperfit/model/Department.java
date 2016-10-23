package com.ripperfit.model;

import java.io.Serializable;

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
@Table(name="department")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="department_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int departmentId;
	
	@Column(name="department_name")
	private String departmentName;
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="organization_id")
	private Organization organization;

	/**
	 * @return the departmentId
	 */
	public int getDepartmentId() {
		return departmentId;
	}


	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}


	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
