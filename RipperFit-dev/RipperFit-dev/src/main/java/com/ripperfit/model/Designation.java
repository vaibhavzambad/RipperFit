package com.ripperfit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="designation_level")
public class Designation {
	
	@Id
	@Column(name="designation_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int designationId;
	
	@Column(name="designation_name")
	private String designationName;
	
	@ManyToMany(cascade={CascadeType.ALL},fetch=FetchType.LAZY)
	@JoinColumn(name="parent_designation_id")
	private List<Designation> designation;
	
	@ManyToMany(mappedBy="designation")
	private List<Designation> subordinates = new ArrayList<Designation>();
	

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
	 * @return the subordinates
	 */
	public List<Designation> getSubordinates() {
		return subordinates;
	}

	/**
	 * @param subordinates the subordinates to set
	 */
	public void setSubordinates(List<Designation> subordinates) {
		this.subordinates = subordinates;
	}
	
}
