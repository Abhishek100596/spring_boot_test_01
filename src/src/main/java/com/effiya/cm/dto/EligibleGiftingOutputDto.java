package com.effiya.cm.dto;

public class EligibleGiftingOutputDto {
	private Integer numberOfEligibleCluster;
	private String status;
	private String description;
	public Integer getNumberOfEligibleCluster() {
		return numberOfEligibleCluster;
	}
	public void setNumberOfEligibleCluster(Integer numberOfEligibleCluster) {
		this.numberOfEligibleCluster = numberOfEligibleCluster;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
