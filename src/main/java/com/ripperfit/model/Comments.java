package com.ripperfit.model;

import java.io.Serializable;
import java.util.Date;

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
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="commentor_id")
	private Employee employee;
	
	@Column(name="comments")
	private String comments;
	
	@ManyToOne(optional=true,fetch=FetchType.LAZY)
	@JoinColumn(name="request_id")
	private ResourceRequest resourceRequest;
	
	@Column(name="comment_date")
	private Date date;

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
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	
	
	/*@ManyToOne(optional=true,fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="organization_id")
	private Organization organization;*/
	
	

}
