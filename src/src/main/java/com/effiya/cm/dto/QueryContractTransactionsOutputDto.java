package com.effiya.cm.dto;

public class QueryContractTransactionsOutputDto {
	
	private String success;
	private QueryContractMessage message;
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public QueryContractMessage getMessage() {
		return message;
	}
	public void setMessage(QueryContractMessage message) {
		this.message = message;
	}
	
	
	
}
