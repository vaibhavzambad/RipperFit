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
@Table(name="resource")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Resource implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="resource_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int resourceId;
	
	@Column(name="resource_name",unique=true)
	private String resourceName;
	
	@Column(name="final_approval_tier_level")
	private int finalApprovalTierLevel;
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="organization_id")
	private Organization organization;
	
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
	 * @return the finalApprovalTierLevel
	 */
	public int getFinalApprovalTierLevel() {
		return finalApprovalTierLevel;
	}

	/**
	 * @param finalApprovalTierLevel the finalApprovalTierLevel to set
	 */
	public void setFinalApprovalTierLevel(int finalApprovalTierLevel) {
		this.finalApprovalTierLevel = finalApprovalTierLevel;
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