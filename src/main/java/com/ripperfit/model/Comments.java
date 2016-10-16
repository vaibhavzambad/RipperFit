package com.ripperfit.model;

import java.io.Serializable;

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
@Table(name="comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comments implements Serializable {
	
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int commentId;
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="commentor_id")
	private Employee employee;
	
	/*@ManyToOne(optional=true,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="organization_id")
	private Organization organization;*/

	/**
	 * @return the commentId
	 */
	public int getCommentId() {
		return commentId;
	}

	/**
	 * @param commentId the commentId to set
	 */
	public void setCommentId(int commentId) {
		this.commentId = commentId;
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
	

}
