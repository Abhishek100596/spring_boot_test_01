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
@Table(name = "CLUSTER_MASTER")
public class ClusterMasterModel extends BaseModel{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cluster_details_sno")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="clusterDetailsSeq")
	@SequenceGenerator(name="clusterDetailsSeq", sequenceName= "clusterDetailsSeq",allocationSize=1)
	private Integer clusterDetailsId;

	@Column(name = "forest_summary_sno")
	private Integer forestSummarySno;
	
	@Column(name = "forest_id")
	private String forestId;
	
	@Column(name = "country_name")
	private String countryName;
	
	@Column(name = "forest_name")
	private String forestName;
	
	@Column(name = "forest_type")
	private String forestType;
	
	@Column(name = "cluster_id")
	private String clusterId;
	
	@Column(name = "cluster_coordinates")
	private String clusterCoordinates;

	@Column(name = "cluster_area")
	private Float clusterArea;
	
	@Column(name = "pin_point")
	private String pinPoint;
	
	@Column(name = "height_in_meters")
	private Float heightInMetres;
	
	@Column(name = "slope_in_degrees")
	private Float slopeInDegrees;
	
	@Column(name = "land_classification")
	private String landClassification;
	
	@Column(name = "surface_type")
	private String surfaceType;
	
	@Column(name = "count_of_trees")
	private Integer countOfTrees;
	
	@Column(name = "ownership_id")
	private String ownershipId;
	
	@Column(name = "is_escrow")
	private String isEscrow;
	
	@Column(name = "last_guardian")
	private String lastGuardian;
	
	@Column(name = "contract_id")
	private String contractId;
	
	

	public String getIsEscrow() {
		return isEscrow;
	}

	public void setIsEscrow(String isEscrow) {
		this.isEscrow = isEscrow;
	}

	public String getLastGuardian() {
		return lastGuardian;
	}

	public void setLastGuardian(String lastGuardian) {
		this.lastGuardian = lastGuardian;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public Integer getForestSummarySno() {
		return forestSummarySno;
	}

	public void setForestSummarySno(Integer forestSummarySno) {
		this.forestSummarySno = forestSummarySno;
	}

	public String getPinPoint() {
		return pinPoint;
	}

	public void setPinPoint(String pinPoint) {
		this.pinPoint = pinPoint;
	}

	public Integer getClusterDetailsId() {
		return clusterDetailsId;
	}

	public void setClusterDetailsId(Integer clusterDetailsId) {
		this.clusterDetailsId = clusterDetailsId;
	}

	public String getClusterId() {
		return clusterId;
	}

	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}

	public String getForestName() {
		return forestName;
	}

	public void setForestName(String forestName) {
		this.forestName = forestName;
	}

	public String getClusterCoordinates() {
		return clusterCoordinates;
	}

	public void setClusterCoordinates(String clusterCoordinates) {
		this.clusterCoordinates = clusterCoordinates;
	}

	public Float getClusterArea() {
		return clusterArea;
	}

	public void setClusterArea(Float clusterArea) {
		this.clusterArea = clusterArea;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getForestId() {
		return forestId;
	}

	public void setForestId(String forestId) {
		this.forestId = forestId;
	}

	public String getForestType() {
		return forestType;
	}

	public void setForestType(String forestType) {
		this.forestType = forestType;
	}

	public Float getHeightInMetres() {
		return heightInMetres;
	}

	public void setHeightInMetres(Float heightInMetres) {
		this.heightInMetres = heightInMetres;
	}

	public Float getSlopeInDegrees() {
		return slopeInDegrees;
	}

	public void setSlopeInDegrees(Float slopeInDegrees) {
		this.slopeInDegrees = slopeInDegrees;
	}

	public String getLandClassification() {
		return landClassification;
	}

	public void setLandClassification(String landClassification) {
		this.landClassification = landClassification;
	}

	public String getSurfaceType() {
		return surfaceType;
	}

	public void setSurfaceType(String surfaceType) {
		this.surfaceType = surfaceType;
	}

	public Integer getCountOfTrees() {
		return countOfTrees;
	}

	public void setCountOfTrees(Integer countOfTrees) {
		this.countOfTrees = countOfTrees;
	}

	public String getOwnershipId() {
		return ownershipId;
	}

	public void setOwnershipId(String ownershipId) {
		this.ownershipId = ownershipId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
