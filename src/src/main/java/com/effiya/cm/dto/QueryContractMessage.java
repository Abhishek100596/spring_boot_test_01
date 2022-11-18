package com.effiya.cm.dto;

import java.util.List;

public class QueryContractMessage {
	
	private List<ContractDetail> contractsDetails;
	private List<ContractDetail> contractDetails;

	public List<ContractDetail> getContractsDetails() {
		return contractsDetails;
	}

	public void setContractsDetails(List<ContractDetail> contractsDetails) {
		this.contractsDetails = contractsDetails;
	}

	public List<ContractDetail> getContractDetails() {
		return contractDetails;
	}

	public void setContractDetails(List<ContractDetail> contractDetails) {
		this.contractDetails = contractDetails;
	}

	
	

	
	
	

}
