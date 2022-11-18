package com.effiya.cm.dto;

public class LimitClusterOutputDto {

	private String status;
	private String description;
	private Integer currentPurchaseEligibility;
	
	
	public Integer getCurrentPurchaseEligibility() {
		return currentPurchaseEligibility;
	}
	public void setCurrentPurchaseEligibility(Integer currentPurchaseEligibility) {
		this.currentPurchaseEligibility = currentPurchaseEligibility;
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
