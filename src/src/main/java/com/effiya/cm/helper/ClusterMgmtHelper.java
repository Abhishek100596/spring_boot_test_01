package com.effiya.cm.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.effiya.cm.dto.ActiveUserInputDto;
import com.effiya.cm.dto.ClusterCoordinates;
import com.effiya.cm.dto.ClusterDetails;
import com.effiya.cm.dto.ContractDetails;
import com.effiya.cm.dto.ContractInfo;
import com.effiya.cm.dto.DeleteContractInputDto;
import com.effiya.cm.dto.RevokeUserInputDto;
import com.effiya.cm.dto.UserIdDto;
import com.effiya.cm.model.ClusterMasterModel;
import com.effiya.cm.model.ContractMasterModel;
import com.effiya.cm.model.UserAssetEventsModel;
import com.effiya.util.CommonUtil;

@Component
public class ClusterMgmtHelper {
	
	@Autowired
	CommonUtil commonUtil;

	public void setValueInUserAssetModel(UserIdDto userIdDto, UserAssetEventsModel userAssetEventsModel) {
		userAssetEventsModel.setEventImpactedId(userIdDto.getUserId());
		userAssetEventsModel.setEvent_reason("NEW USER IS CREATED");
		userAssetEventsModel.setEventType("CREATE");
		userAssetEventsModel.setEventOn("USER");
		userAssetEventsModel.setCreatedBy("SYSTEM");
		userAssetEventsModel.setCreatedDt(new java.sql.Date(new java.util.Date().getTime()));
		userAssetEventsModel.setCreatedDttm(new java.sql.Timestamp(new java.util.Date().getTime()));
	}

	public void setRevokeInModel(UserAssetEventsModel userAssetEventsModel, RevokeUserInputDto revokeUserInputDto) {
		userAssetEventsModel.setEventType("REVOKE");
		userAssetEventsModel.setEventImpactedId(revokeUserInputDto.getUserId());
		userAssetEventsModel.setEvent_reason(revokeUserInputDto.getReason());
		userAssetEventsModel.setCreatedBy("SYSTEM");
		userAssetEventsModel.setEventOn("USER");
		userAssetEventsModel.setCreatedDt(new java.sql.Date(new java.util.Date().getTime()));
		userAssetEventsModel.setCreatedDttm(new java.sql.Timestamp(new java.util.Date().getTime()));
	}

	public void setActivateInModel(UserAssetEventsModel userAssetEventsModel, ActiveUserInputDto activeUserInputDto) {
		userAssetEventsModel.setEventType("ACTIVATE");
		userAssetEventsModel.setEventImpactedId(activeUserInputDto.getUserId());
		userAssetEventsModel.setEvent_reason(activeUserInputDto.getReason());
		userAssetEventsModel.setCreatedBy("SYSTEM");
		userAssetEventsModel.setEventOn("USER");
		userAssetEventsModel.setCreatedDt(new java.sql.Date(new java.util.Date().getTime()));
		userAssetEventsModel.setCreatedDttm(new java.sql.Timestamp(new java.util.Date().getTime()));
	}

	public void setDeleteContractInModel(UserAssetEventsModel userAssetEventsModel, DeleteContractInputDto deleteContractInputDto) {
		userAssetEventsModel.setEventImpactedId(deleteContractInputDto.getContractId().toString());
		userAssetEventsModel.setEvent_reason(deleteContractInputDto.getReason());
		userAssetEventsModel.setEventOn("CONTRACT");
		userAssetEventsModel.setEventType("DELETE");
		userAssetEventsModel.setCreatedBy("SYSTEM");
		userAssetEventsModel.setCreatedDt(new java.sql.Date(new java.util.Date().getTime()));
		userAssetEventsModel.setCreatedDttm(new java.sql.Timestamp(new java.util.Date().getTime()));
	}

	public ClusterDetails convertClusterMasterModelToClusterDetails(ClusterMasterModel cluster) {
		ClusterDetails clusterDetails = new ClusterDetails();
		clusterDetails.setCluster_id(cluster.getClusterId());
		clusterDetails.setForest_id(cluster.getForestId());
		clusterDetails.setOwner_id(null);
		clusterDetails.setIs_booked("0");
		clusterDetails.setCountry_name(cluster.getCountryName());
		ClusterCoordinates cluster_coordinates = getPinPoint(cluster);
		clusterDetails.setCluster_coordinates(cluster_coordinates);
		clusterDetails.setIs_active("1");
		clusterDetails.setMiscellaneous("");
		return clusterDetails;
	}

	private ClusterCoordinates getPinPoint(ClusterMasterModel cluster) {
		ClusterCoordinates cluster_coordinates = new ClusterCoordinates();
		String str = cluster.getPinPoint();
		str = str.replace("(", "");
		str = str.replace(")", "");
		str = str.trim();
		String s[] = str.split(",");
		String x = s[0];
		String y = s[1];
		cluster_coordinates.setLattitude(x);
		cluster_coordinates.setLongitude(y);
		return cluster_coordinates;
	}

	public Map<String, List<ClusterMasterModel>> mapClustersByContract(List<ClusterMasterModel> clusters) {
		Map<String, List<ClusterMasterModel>> clustersByContractMap = new HashMap<>();
		for(ClusterMasterModel cluster : clusters) {
			if(clustersByContractMap.containsKey(cluster.getContractId())) {
				clustersByContractMap.get(cluster.getContractId()).add(cluster);
			}
			else {
				List<ClusterMasterModel> clusterList = new ArrayList<>();
				clustersByContractMap.put(cluster.getContractId(), clusterList);
			}
		}
		return clustersByContractMap;
	}

	public ContractDetails convertContractMasterModelToContractDetailsForAdd(ContractMasterModel contract,
			List<ClusterMasterModel> clusters, String giftStatus) {
		ContractDetails contractDetails = new ContractDetails();
		contractDetails.setContract_id(contract.getContractId());
		contractDetails.setOwner_id(contract.getUserId());
		contractDetails.setContract_country(contract.getContractCountry());
		contractDetails.setPayment_ref(contract.getTransactionRefNo());
		contractDetails.setContract_start_date(commonUtil.convertSqlDateToStringDate(contract.getContractStartDate()));
		contractDetails.setContract_expiry_date(commonUtil.convertSqlDateToStringDate(contract.getContractExpiryDate()));
		contractDetails.setNumber_of_clusters(commonUtil.integerToString(clusters.size()));
		List<String> clusterIds = new ArrayList<>();
		for(ClusterMasterModel cluster : clusters) {
			clusterIds.add(cluster.getClusterId());
		}
		contractDetails.setList_of_clusters(clusterIds);
		if(giftStatus.equalsIgnoreCase("ACCEPT")) {
			contractDetails.setIs_gifted("1");
		}
		else {
			contractDetails.setIs_gifted("0");
		}
		contractDetails.setIs_active("1");
		return contractDetails;
	}

	public ContractDetails convertContractMasterModelToContractDetailsForEdit(ContractMasterModel contract,
			List<ClusterMasterModel> clusters, String giftStatus, List<String> clusterIds, String clusterAction) {
		ContractDetails contractDetails = new ContractDetails();
		contractDetails.setContract_id(contract.getContractId());
		contractDetails.setOwner_id(contract.getUserId());
		contractDetails.setContract_country(contract.getContractCountry());
		contractDetails.setPayment_ref(contract.getTransactionRefNo());
		contractDetails.setContract_start_date(commonUtil.convertSqlDateToStringDate(contract.getContractStartDate()));
		contractDetails.setContract_expiry_date(commonUtil.convertSqlDateToStringDate(contract.getContractExpiryDate()));
		contractDetails.setNumber_of_clusters(commonUtil.integerToString(clusterIds.size()));
		contractDetails.setCluster_action(clusterAction);
		contractDetails.setList_of_clusters(clusterIds);
		if(giftStatus.equalsIgnoreCase("ACCEPT")) {
			contractDetails.setIs_gifted("1");
		}
		else {
			contractDetails.setIs_gifted("0");
		}
		contractDetails.setIs_active("1");
		return contractDetails;
	}

	public ContractInfo convertContractMasterModelToContractInfo(ContractMasterModel contract) {
		ContractInfo contractInfo = new ContractInfo();
		contractInfo.setContractClusterDemand(contract.getContract_cluster_demand().toString());
		contractInfo.setContractCountry(contract.getContractCountry());
		contractInfo.setContractExpiryDate(commonUtil.convertSqlDateToStringDate(contract.getContractExpiryDate()));
		contractInfo.setContractId(contract.getContractId());
		contractInfo.setContractStartDate(commonUtil.convertSqlDateToStringDate(contract.getContractStartDate()));
		contractInfo.setCreatedBy(contract.getCreatedBy());
		contractInfo.setCreatedDttm(commonUtil.convertSqlTimestampToStringDate(contract.getCreatedDttm()));
		contractInfo.setCurrentStatus(contract.getCurrentStatus());
		contractInfo.setGiftGiverId(contract.getGiftGiverId());
		contractInfo.setGiftTakerId(contract.getGiftTakerId());
		contractInfo.setIsEscrow(contract.getIsEscrow());
		if(contract.isGift()) {
			contractInfo.setIsGift("true");
		}
		else {
			contractInfo.setIsGift("false");
		}
		contractInfo.setNewPurchaseFlag(contract.getNewPurchaseFlag());
		contractInfo.setStatusReason(contract.getStatusReason());
		contractInfo.setTransactionRefNo(contract.getTransactionRefNo());
		contractInfo.setTransactionTimestamp(commonUtil.convertSqlTimestampToStringDate(contract.getTransactionTimestamp()));
		contractInfo.setUpdatedDttm(commonUtil.convertSqlTimestampToStringDate(contract.getUpdatedDttm()));
		contractInfo.setUserId(contract.getUserId());
		return contractInfo;
	}

}
