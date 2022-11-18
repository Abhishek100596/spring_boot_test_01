package com.effiya.cm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author Abhishek Kumar Singh
 */

@Entity
@Table(name = "CONTRACT_MASTER_HISTORY")
public class ContractMasterHistoryModel extends BaseModel{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="contract_master_history_sno")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="contractMasterHistorySeq")
	@SequenceGenerator(name="contractMasterHistorySeq", sequenceName= "contractMasterHistorySeq",allocationSize=1)
	private Integer contractMasterHistorySno;

//	@Column(name = "contract_master_sno")
//	private Integer contractMasterSno;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_master_sno", referencedColumnName = "CONTRACT_MASTER_SNO")
	private ContractMasterModel contractMasterModel;
	
	@Column(name = "transaction_ref_no")
	private String transactionRefNo;
	
	@Column(name = "contract_start_date")
	private java.sql.Date contractStartDate;
	
	@Column(name = "contract_expiry_date")
	private java.sql.Date contractExpiryDate;
	
	@Column(name = "contract_cluster_demand")
	private Integer contractClusterDemand;
	
	@Column(name = "is_gift")
	private boolean isGift;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "gift_giver_id")
	private String giftGiverId;
	
	@Column(name = "gift_taker_id")
	private String giftTakerId;
	
	@Column(name = "contract_country")
	private String contract_country;
	
	@Column(name = "new_purchase_flag")
	private String new_purchase_flag;
	
	@Column(name = "current_status")
	private String current_status;
	
	@Column(name = "contract_id")
	private Integer contractId;
	
	@Column(name = "cluster_id")
	private String cluster_id;

	public ContractMasterModel getContractMasterModel() {
		return contractMasterModel;
	}

	public void setContractMasterModel(ContractMasterModel contractMasterModel) {
		this.contractMasterModel = contractMasterModel;
	}

	public Integer getContractMasterHistorySno() {
		return contractMasterHistorySno;
	}

	public void setContractMasterHistorySno(Integer contractMasterHistorySno) {
		this.contractMasterHistorySno = contractMasterHistorySno;
	}

	public String getTransactionRefNo() {
		return transactionRefNo;
	}

	public void setTransactionRefNo(String transactionRefNo) {
		this.transactionRefNo = transactionRefNo;
	}

	public java.sql.Date getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(java.sql.Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public java.sql.Date getContractExpiryDate() {
		return contractExpiryDate;
	}

	public void setContractExpiryDate(java.sql.Date contractExpiryDate) {
		this.contractExpiryDate = contractExpiryDate;
	}

	public Integer getContractClusterDemand() {
		return contractClusterDemand;
	}

	public void setContractClusterDemand(Integer contractClusterDemand) {
		this.contractClusterDemand = contractClusterDemand;
	}

	public boolean isGift() {
		return isGift;
	}

	public void setGift(boolean isGift) {
		this.isGift = isGift;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getContract_country() {
		return contract_country;
	}

	public void setContract_country(String contract_country) {
		this.contract_country = contract_country;
	}

	public String getNew_purchase_flag() {
		return new_purchase_flag;
	}

	public void setNew_purchase_flag(String new_purchase_flag) {
		this.new_purchase_flag = new_purchase_flag;
	}

	public String getCurrent_status() {
		return current_status;
	}

	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public String getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(String cluster_id) {
		this.cluster_id = cluster_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
