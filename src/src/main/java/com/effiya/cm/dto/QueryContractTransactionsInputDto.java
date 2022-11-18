package com.effiya.cm.dto;

public class QueryContractTransactionsInputDto {
	
	private String contractId;
	private String clusterId;
	private String paymentReferenceNo;
	private String userId;
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getClusterId() {
		return clusterId;
	}
	public void setClusterId(String clusterId) {
		this.clusterId = clusterId;
	}
	public String getPaymentReferenceNo() {
		return paymentReferenceNo;
	}
	public void setPaymentReferenceNo(String paymentReferenceNo) {
		this.paymentReferenceNo = paymentReferenceNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
