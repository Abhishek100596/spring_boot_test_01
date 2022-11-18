package com.effiya.cm.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TakerGiftingScenarioOutputDto {
	
	private String status;
	private String description;
	private String giftType;
	@JsonIgnore
	private String impactedContracts;
	
	
	public String getImpactedContracts() {
		return impactedContracts;
	}
	public void setImpactedContracts(String impactedContracts) {
		this.impactedContracts = impactedContracts;
	}
	public String getGiftType() {
		return giftType;
	}
	public void setGiftType(String giftType) {
		this.giftType = giftType;
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
