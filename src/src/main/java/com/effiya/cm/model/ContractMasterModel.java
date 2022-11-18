package com.effiya.cm.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * @author Abhishek Kumar Singh
 */

@Entity
@Table(name = "CONTRACT_MASTER")
public class ContractMasterModel extends BaseModel{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CONTRACT_MASTER_SNO")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="sontractMasterSeq")
	@SequenceGenerator(name="sontractMasterSeq", sequenceName= "sontractMasterSeq",allocationSize=1)
	private Integer sontractMasterId;
	
	@Column(name = "CONTRACT_ID")
	private String contractId;

	@Column(name = "TRANSACTION_REF_NO")
	private String transactionRefNo;
	
	@Column(name = "TRANSACTION_TIMESTAMP")
	private Timestamp transactionTimestamp;
	
	@Column(name = "LAST_12_MONTHS_SGC")
	private Integer last_12_months_sgc;
	
	@Column(name = "CONTRACT_START_DATE")
	private java.sql.Date contractStartDate;
	
	@Column(name = "CONTRACT_EXPIRY_DATE")
	private java.sql.Date contractExpiryDate;
	
	@Column(name = "CONTRACT_CLUSTER_DEMAND")
	private Integer contract_cluster_demand;
	
	@Column(name = "IS_GIFT")
	private boolean isGift;

	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "GIFT_GIVER_ID")
	private String giftGiverId;
	
	@Column(name = "GIFT_TAKER_ID")
	private String giftTakerId;
	
	@Column(name = "CONTRACT_COUNTRY")
	private String contractCountry;
	
	@Column(name = "REFERRAL_CODE")
	private String referralCode;
	
	@Column(name = "NEW_PURCHASE_FLAG")
	private String newPurchaseFlag;
	
	@Column(name = "CURRENT_STATUS")
	private String currentStatus;
	
	@Column(name = "IS_ESCROW")
	private String isEscrow;
	
	@Column(name = "STATUS_REASON")
	private String statusReason;
	
//	@OneToOne(mappedBy = "CONTRACT_MASTER")
//    private ContractMasterHistoryModel contractMasterHistoryModel;
	
	

	public Integer getSontractMasterId() {
		return sontractMasterId;
	}

	public String getStatusReason() {
		return statusReason;
	}

	public void setStatusReason(String statusReason) {
		this.statusReason = statusReason;
	}

	public String getIsEscrow() {
		return isEscrow;
	}

	public void setIsEscrow(String isEscrow) {
		this.isEscrow = isEscrow;
	}

	public void setSontractMasterId(Integer sontractMasterId) {
		this.sontractMasterId = sontractMasterId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getTransactionRefNo() {
		return transactionRefNo;
	}

	public void setTransactionRefNo(String transactionRefNo) {
		this.transactionRefNo = transactionRefNo;
	}

	public Timestamp getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(Timestamp transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

	public Integer getLast_12_months_sgc() {
		return last_12_months_sgc;
	}

	public void setLast_12_months_sgc(Integer last_12_months_sgc) {
		this.last_12_months_sgc = last_12_months_sgc;
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

	public Integer getContract_cluster_demand() {
		return contract_cluster_demand;
	}

	public void setContract_cluster_demand(Integer contract_cluster_demand) {
		this.contract_cluster_demand = contract_cluster_demand;
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

	public String getContractCountry() {
		return contractCountry;
	}

	public void setContractCountry(String contractCountry) {
		this.contractCountry = contractCountry;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public String getNewPurchaseFlag() {
		return newPurchaseFlag;
	}

	public void setNewPurchaseFlag(String newPurchaseFlag) {
		this.newPurchaseFlag = newPurchaseFlag;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
