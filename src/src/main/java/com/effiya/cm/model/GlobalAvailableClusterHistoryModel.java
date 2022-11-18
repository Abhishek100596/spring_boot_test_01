package com.effiya.cm.model;

import java.sql.Timestamp;

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
@Table(name = "CONTRACT_REQUEST_STATUS_HISTORY")
public class GlobalAvailableClusterHistoryModel {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="global_available_clusters_history_sno")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="globalAvailableClustersHistorySeq")
	@SequenceGenerator(name="globalAvailableClustersHistorySeq", sequenceName= "globalAvailableClustersHistorySeq",allocationSize=1)
	private Integer globalAvailableClustersHistorySno;

//	@Column(name = "global_available_clusters_sno")
//	private Integer  globalAvailableClustersSno;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "global_available_clusters_sno", referencedColumnName = "global_available_clusters_sno")
	private GlobalAvailableClustersModel globalAvailableClustersModel;
	
	@Column(name = "current_available_clusters")
	private Integer  currentAvailableClusters;
	
	@Column(name = "updated_timestamp")
	private Timestamp updatedTimestamp;
	
	@Column(name = "updated_dt")
	private java.sql.Date updatedDt;
	
	@Column(name = "updated_by")
	private String  updatedBy;

	public Integer getGlobalAvailableClustersHistorySno() {
		return globalAvailableClustersHistorySno;
	}

	public void setGlobalAvailableClustersHistorySno(Integer globalAvailableClustersHistorySno) {
		this.globalAvailableClustersHistorySno = globalAvailableClustersHistorySno;
	}

	public GlobalAvailableClustersModel getGlobalAvailableClustersModel() {
		return globalAvailableClustersModel;
	}

	public void setGlobalAvailableClustersModel(GlobalAvailableClustersModel globalAvailableClustersModel) {
		this.globalAvailableClustersModel = globalAvailableClustersModel;
	}

	public Integer getCurrentAvailableClusters() {
		return currentAvailableClusters;
	}

	public void setCurrentAvailableClusters(Integer currentAvailableClusters) {
		this.currentAvailableClusters = currentAvailableClusters;
	}

	public Timestamp getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	public void setUpdatedTimestamp(Timestamp updatedTimestamp) {
		this.updatedTimestamp = updatedTimestamp;
	}

	public java.sql.Date getUpdatedDt() {
		return updatedDt;
	}

	public void setUpdatedDt(java.sql.Date updatedDt) {
		this.updatedDt = updatedDt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
