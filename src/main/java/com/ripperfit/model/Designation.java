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
@Table(name="designation")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Designation implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="designation_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int designationId;
	
	@Column(name="designation_name")
	private String designationName;
	
	/*@ManyToOne(optional=false,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="organization_id")
	private Organization organization;*/
	
	@Column(name="designation_level")
	private int designationLevel;
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="department_id")
	private Department department;

	/**
	 * @return the designationId
	 */
	public int getDesignationId() {
		return designationId;
	}

	/**
	 * @param designationId the designationId to set
	 */
	public void setDesignationId(int designationId) {
		this.designationId = designationId;
	}

	/**
	 * @return the designationName
	 */
	public String getDesignationName() {
		return designationName;
	}

	/**
	 * @param designationName the designationName to set
	 */
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
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
	 * @return the designationLevel
	 */
	public int getDesignationLevel() {
		return designationLevel;
	}

	/**
	 * @param designationLevel the designationLevel to set
	 */
	public void setDesignationLevel(int designationLevel) {
		this.designationLevel = designationLevel;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}
	
}
