package com.ripperfit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="resource")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Resource implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="resource_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resourceId;
	
	@Column(name="resource_name")
	private String resourceName;
	
	@Column(name="final_approval_level")
	private int finalApprovalLevel;
	
/*	@ManyToOne(optional=false,fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="organization_id")
	private Organization organization;*/
	
	@Column(name="quantity")
	private int quantity;

	/**
	 * @return the resourceId
	 */
	public int getResourceId() {
		return resourceId;
	}

	/**
	 * @param resourceId the resourceId to set
	 */
	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 * @return the resourceName
	 */
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * @param resourceName the resourceName to set
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * @return the finalApprovalLevel
	 */
	public int getFinalApprovalLevel() {
		return finalApprovalLevel;
	}

	/**
	 * @param finalApprovalLevel the finalApprovalLevel to set
	 */
	public void setFinalApprovalLevel(int finalApprovalLevel) {
		this.finalApprovalLevel = finalApprovalLevel;
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
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}