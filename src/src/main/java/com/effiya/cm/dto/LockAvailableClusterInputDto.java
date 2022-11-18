package com.effiya.cm.dto;

public class LockAvailableClusterInputDto {

	private String userId;
	private Integer requestedSgc;
	private String scenario;
	
	
	public String getScenario() {
		return scenario;
	}
	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Integer getRequestedSgc() {
		return requestedSgc;
	}
	public void setRequestedSgc(Integer requestedSgc) {
		this.requestedSgc = requestedSgc;
	}
}
