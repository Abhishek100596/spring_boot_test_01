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
@Table(name = "CONTRACT_REQUEST_STATUS")
public class ContractRequestStatusModel {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="contract_request_status_sno")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="contractRequestStatusSeq")
	@SequenceGenerator(name="contractRequestStatusSeq", sequenceName= "contractRequestStatusSeq",allocationSize=1)
	private Integer contractRequestStatusSno;
	
	@Column(name = "mq_message")
	private String mqMessage;
	
	@Column(name = "mg_message_timestamp")
	private Timestamp mgMessageTimestamp;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "contract_id")
	private String contractId;
	
	@Column(name = "request_type")
	private String requestType;
	
	@Column(name = "gift_giver_id")
	private String giftGiverId;
	
	@Column(name = "gift_taker_id")
	private String giftTakerId;

	@Column(name = "temp_hold_timestamp")
	private Timestamp tempHoldTimestamp;
	
	@Column(name = "temp_release_timestamp")
	private Timestamp tempReleaseTimestamp;
	
	@Column(name = "contract_master_update_time")
	private Timestamp contractMasterUdateTime;
	
	@Column(name = "blockchain_post1_timestamp")
	private Timestamp blockchainPost1Timestamp;
	
	@Column(name = "blockchain_post2_timestamp")
	private Timestamp blockchainPost2Timestamp;
	
	@Column(name = "blockchain_post1_status")
	private String blockchainPost1Status;
	
	@Column(name = "blockchain_post2_status")
	private String blockchainPost2Status;
	
//	@OneToOne(mappedBy = "CONTRACT_REQUEST_STATUS")
//    private ContractRequestStatusHistoryModel contractRequestStatusHistoryModel;

	public Integer getContractRequestStatusSno() {
		return contractRequestStatusSno;
	}

	public void setContractRequestStatusSno(Integer contractRequestStatusSno) {
		this.contractRequestStatusSno = contractRequestStatusSno;
	}

	public String getMqMessage() {
		return mqMessage;
	}

	public void setMqMessage(String mqMessage) {
		this.mqMessage = mqMessage;
	}

	public Timestamp getMgMessageTimestamp() {
		return mgMessageTimestamp;
	}

	public void setMgMessageTimestamp(Timestamp mgMessageTimestamp) {
		this.mgMessageTimestamp = mgMessageTimestamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
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

	public Timestamp getTempHoldTimestamp() {
		return tempHoldTimestamp;
	}

	public void setTempHoldTimestamp(Timestamp tempHoldTimestamp) {
		this.tempHoldTimestamp = tempHoldTimestamp;
	}

	public Timestamp getTempReleaseTimestamp() {
		return tempReleaseTimestamp;
	}

	public void setTempReleaseTimestamp(Timestamp tempReleaseTimestamp) {
		this.tempReleaseTimestamp = tempReleaseTimestamp;
	}

	public Timestamp getContractMasterUdateTime() {
		return contractMasterUdateTime;
	}

	public void setContractMasterUdateTime(Timestamp contractMasterUdateTime) {
		this.contractMasterUdateTime = contractMasterUdateTime;
	}

	public Timestamp getBlockchainPost1Timestamp() {
		return blockchainPost1Timestamp;
	}

	public void setBlockchainPost1Timestamp(Timestamp blockchainPost1Timestamp) {
		this.blockchainPost1Timestamp = blockchainPost1Timestamp;
	}

	public Timestamp getBlockchainPost2Timestamp() {
		return blockchainPost2Timestamp;
	}

	public void setBlockchainPost2Timestamp(Timestamp blockchainPost2Timestamp) {
		this.blockchainPost2Timestamp = blockchainPost2Timestamp;
	}

	public String getBlockchainPost1Status() {
		return blockchainPost1Status;
	}

	public void setBlockchainPost1Status(String blockchainPost1Status) {
		this.blockchainPost1Status = blockchainPost1Status;
	}

	public String getBlockchainPost2Status() {
		return blockchainPost2Status;
	}

	public void setBlockchainPost2Status(String blockchainPost2Status) {
		this.blockchainPost2Status = blockchainPost2Status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
