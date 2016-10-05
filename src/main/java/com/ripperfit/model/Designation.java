package com.ripperfit.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="designation")
public class Designation {
	
	@Id
	@Column(name="designation_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int designationId;
	
	@Column(name="designation_name")
	private String designationName;
	
	@OneToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="parent_designation_id")
	private Designation designation;

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
	 * @return the parentDesignationId
	 */
	public Designation getParentDesignationId() {
		return designation;
	}

	/**
	 * @param parentDesignationId the parentDesignationId to set
	 */
	public void setParentDesignationId(Designation designation) {
		this.designation = designation;
	} 
	
}
