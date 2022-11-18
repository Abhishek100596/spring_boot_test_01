package com.effiya.cm.dto;

public class EligibleGiftingInputDto {

	private String userId;
	private Integer requestedSgc;
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
