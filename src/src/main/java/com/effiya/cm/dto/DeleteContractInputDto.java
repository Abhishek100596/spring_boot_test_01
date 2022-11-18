package com.effiya.cm.dto;

public class DeleteContractInputDto {

	private Integer contractId;
	private String reason;
	public Integer getContractId() {
		return contractId;
	}
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
