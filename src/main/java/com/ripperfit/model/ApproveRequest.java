package com.ripperfit.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="comments")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ApproveRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="approvee_id")
	private int approveeId;
	
	@Id
	@Column(name="request_id")
	private int requestId;

	/**
	 * @return the approveeId
	 */
	public int getApproveeId() {
		return approveeId;
	}

	/**
	 * @param approveeId the approveeId to set
	 */
	public void setApproveeId(int approveeId) {
		this.approveeId = approveeId;
	}

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
}
