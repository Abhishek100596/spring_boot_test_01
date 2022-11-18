package com.effiya.cm.model;


import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author Abhishek Kumar Singh
 */

@MappedSuperclass
public class BaseModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_DTTM")
	private Timestamp createdDttm;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DTTM")
	private Timestamp updatedDttm;
	
	@Column(name = "CREATED_DATE")
	private java.sql.Date createdDt;
	
	@Column(name = "UPDATED_DATE")
	private java.sql.Date updatedDt;
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDttm() {
		return createdDttm;
	}

	public void setCreatedDttm(Timestamp createdDttm) {
		this.createdDttm = createdDttm;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Timestamp getUpdatedDttm() {
		return updatedDttm;
	}

	public void setUpdatedDttm(Timestamp updatedDttm) {
		this.updatedDttm = updatedDttm;
	}

	public java.sql.Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(java.sql.Date createdDt) {
		this.createdDt = createdDt;
	}

	public java.sql.Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(java.sql.Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	@PreUpdate
	protected void onPreUpdate() {
		this.updatedDt = new java.sql.Date(new Date().getTime());
	}

	@PrePersist
	protected void onPrePersist() {
		final Date now = new Date();
		this.createdDttm = new Timestamp(now.getTime());
		this.updatedDttm = createdDttm;
		this.createdDt = new java.sql.Date(now.getTime());
		this.updatedDt = createdDt;
	}
	
}
