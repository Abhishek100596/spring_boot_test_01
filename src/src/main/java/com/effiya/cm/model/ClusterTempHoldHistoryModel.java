package com.effiya.cm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Abhishek Kumar Singh
 */

@Entity
@Table(name = "CLUSTER_TEMP_HOLD_HISTORY")
public class ClusterTempHoldHistoryModel extends BaseModel{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cthh_sno")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="cthhSeq")
	@SequenceGenerator(name="cthhSeq", sequenceName= "cthhSeq",allocationSize=1)
	private Integer cthhSno;

//	@Column(name = "cth_sno")
//	private Integer cth_sno;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cth_sno", referencedColumnName = "cluster_temp_hold_sno")
	private ClusterTempHoldModel clusterTempHoldModel;
	
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

	public Integer getCthhSno() {
		return cthhSno;
	}

	public void setCthhSno(Integer cthhSno) {
		this.cthhSno = cthhSno;
	}

	public ClusterTempHoldModel getClusterTempHoldModel() {
		return clusterTempHoldModel;
	}

	public void setClusterTempHoldModel(ClusterTempHoldModel clusterTempHoldModel) {
		this.clusterTempHoldModel = clusterTempHoldModel;
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
