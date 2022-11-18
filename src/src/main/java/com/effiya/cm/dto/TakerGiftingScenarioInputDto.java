package com.effiya.cm.dto;

public class TakerGiftingScenarioInputDto {
	
	private String giftGiverId;
	private String giftTakerId;
	private String reqScg;
	private String referralCode;
	private String giftStatus;
	private String contractPeriod;
	
	
	public String getContractPeriod() {
		return contractPeriod;
	}
	public void setContractPeriod(String contractPeriod) {
		this.contractPeriod = contractPeriod;
	}
	public String getGiftGiverId() {
		return giftGiverId;
	}
	public void setGiftGiverId(String giftGiverId) {
		this.giftGiverId = giftGiverId;
	}
	public String getGiftTakerId() {
		return giftTakerId;
	}
	public void setGiftTakerId(String giftTakerId) {
		this.giftTakerId = giftTakerId;
	}
	public String getReqScg() {
		return reqScg;
	}
	public void setReqScg(String reqScg) {
		this.reqScg = reqScg;
	}
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	public String getGiftStatus() {
		return giftStatus;
	}
	public void setGiftStatus(String giftStatus) {
		this.giftStatus = giftStatus;
	}
	
	
	
	
}
