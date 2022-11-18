package com.effiya.cm.model;

import java.sql.Timestamp;

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
@Table(name = "GLOBAL_AVAILABLE_CLUSTERS")
public class GlobalAvailableClustersModel {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="global_available_clusters_sno")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="globalAvailableClustersSeq")
	@SequenceGenerator(name="globalAvailableClustersSeq", sequenceName= "globalAvailableClustersSeq",allocationSize=1)
	private Integer globalAvailableClustersSno;

	@Column(name = "current_available_clusters")
	private Integer currentAvailableClusters;
	
	@Column(name = "updated_timestamp")
	private Timestamp updatedTimestamp;
	
	@Column(name = "updated_dt")
	private java.sql.Date updatedDt;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
//	@OneToOne(mappedBy = "GLOBAL_AVAILABLE_CLUSTERS")
//    private GlobalAvailableClusterHistoryModel globalAvailableClusterHistoryModel;


	public Integer getCurrentAvailableClusters() {
		return currentAvailableClusters;
	}

	public Integer getGlobalAvailableClustersSno() {
		return globalAvailableClustersSno;
	}

	public void setGlobalAvailableClustersSno(Integer globalAvailableClustersSno) {
		this.globalAvailableClustersSno = globalAvailableClustersSno;
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
