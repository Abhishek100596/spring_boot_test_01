package com.effiya.cm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Abhishek Kumar Singh
 */

@Entity
@Table(name = "CLUSTER_TEMP_HOLD")
public class ClusterTempHoldModel extends BaseModel{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cluster_temp_hold_sno")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="clusterTempHoldSeq")
	@SequenceGenerator(name="clusterTempHoldSeq", sequenceName= "clusterTempHoldSeq",allocationSize=1)
	private Integer clusterTempHoldSno;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "cluster_requirement")
	private Integer clusterRequirement;
	
	@Column(name = "cluster_availiblity_pit")
	private Integer clusterAvailiblityPit;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "status_reason")
	private String statusReason;

	public Integer getClusterTempHoldSno() {
		return clusterTempHoldSno;
	}

	public void setClusterTempHoldSno(Integer clusterTempHoldSno) {
		this.clusterTempHoldSno = clusterTempHoldSno;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getClusterRequirement() {
		return clusterRequirement;
	}

	public void setClusterRequirement(Integer clusterRequirement) {
		this.clusterRequirement = clusterRequirement;
	}

	public Integer getClusterAvailiblityPit() {
		return clusterAvailiblityPit;
	}

	public void setClusterAvailiblityPit(Integer clusterAvailiblityPit) {
		this.clusterAvailiblityPit = clusterAvailiblityPit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
