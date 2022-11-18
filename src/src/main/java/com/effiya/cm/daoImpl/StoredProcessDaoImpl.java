package com.effiya.cm.daoImpl;

import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.effiya.cm.dao.ContractMasterModelDao;
import com.effiya.cm.dao.StoredProcessDao;
import com.effiya.cm.dto.DeleteContractInputDto;
import com.effiya.cm.dto.DeleteContractOutputDto;
import com.effiya.cm.dto.EligibleGiftingInputDto;
import com.effiya.cm.dto.EligibleGiftingOutputDto;
import com.effiya.cm.dto.LimitClusterInputDto;
import com.effiya.cm.dto.LimitClusterOutputDto;
import com.effiya.cm.dto.LockAvailableClusterInputDto;
import com.effiya.cm.dto.LockAvailableClusterOutputDto;
import com.effiya.cm.dto.TakerGiftingScenarioInputDto;
import com.effiya.cm.dto.TakerGiftingScenarioOutputDto;
import com.effiya.cm.dto.TransactionListenerInputDto;
import com.effiya.cm.dto.TransactionListenerOutputDto;
import com.effiya.cm.dto.TransactionScenarioWorkspaceDto;
import com.effiya.util.CommonUtil;


/**
 * @author Abhishek Kumar Singh
 */


@Repository
public class StoredProcessDaoImpl implements StoredProcessDao{

	private static Logger logger = LoggerFactory.getLogger(StoredProcessDaoImpl.class);
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	ContractMasterModelDao contractMasterModelDao;

	@Override
	public void deleteContractStoredProcess(DeleteContractInputDto deleteContractInputDto,
			DeleteContractOutputDto deleteContractOutputDto) {
		try {
		SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_delete_contract");
		SqlParameterSource in = new MapSqlParameterSource().addValue("contract_id", deleteContractInputDto.getContractId()).addValue("reason", deleteContractInputDto.getReason());
		Map<String, Object> out = jdbcCall.execute(in);
		System.out.println(out);
		if(out.containsKey("success")&&out.get("success").toString().equalsIgnoreCase("false")) {
			deleteContractOutputDto.setMessage("Error executing stored procedure");
		}
		}
		catch(Exception e) {
			logger.error("Exception :",e);
		}
	}

	@Override
	public void lockAvailableClusterStoredProcess(LockAvailableClusterInputDto lockAvailableClusterInputDto,
			LockAvailableClusterOutputDto lockAvailableClusterOutputDto) {
		try {
		SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_lock_avail_cluster");
		SqlParameterSource in = new MapSqlParameterSource().addValue("user_id", lockAvailableClusterInputDto.getUserId()).addValue("requested_sgc", lockAvailableClusterInputDto.getRequestedSgc());
		Map<String, Object> out = jdbcCall.execute(in);
		lockAvailableClusterOutputDto.setStatus(out.get("status").toString());
		lockAvailableClusterOutputDto.setDescription(out.get("description").toString());
		}
		catch(Exception e) {
			logger.error("Exception :",e);
		}
	}

	@Override
	public void eligibleGiftingStoredProcess(EligibleGiftingInputDto eligibleGiftingInputDto,
			EligibleGiftingOutputDto eligibleGiftingOutputDto) {
		try {
			SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_eligible_gifting");
			SqlParameterSource in = new MapSqlParameterSource().addValue("user_id", eligibleGiftingInputDto.getUserId()).addValue("requested_sgc", eligibleGiftingInputDto.getRequestedSgc());
			Map<String, Object> out = jdbcCall.execute(in);
			eligibleGiftingOutputDto.setStatus(out.get("status").toString());
			eligibleGiftingOutputDto.setDescription(out.get("description").toString());
			if(null!=out.get("number_of_eligible_cluster")) {
			eligibleGiftingOutputDto.setNumberOfEligibleCluster(Integer.parseInt(out.get("number_of_eligible_cluster").toString()));
			}
			}
			catch(Exception e) {
				logger.error("Exception :",e);
			}
		
	}

	@Override
	public void limitClusterStoredProcess(LimitClusterInputDto limitClusterInputDto,
			LimitClusterOutputDto limitClusterOutputDto) {
		try {
			SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_limit_cluster");
			SqlParameterSource in = new MapSqlParameterSource().addValue("user_id", limitClusterInputDto.getUserId()).addValue("requested_sgc", limitClusterInputDto.getRequestedSgc());
			Map<String, Object> out = jdbcCall.execute(in);
			limitClusterOutputDto.setStatus(out.get("status").toString());
			limitClusterOutputDto.setDescription(out.get("description").toString());
			if(null!=out.get("current_purchase_eligibility")) {
				limitClusterOutputDto.setCurrentPurchaseEligibility(Integer.parseInt(out.get("current_purchase_eligibility").toString()));
			}
			}
			catch(Exception e) {
				logger.error("Exception :",e);
			}
	}

	@Override
	public void transactionListenerStoredProcess(TransactionListenerInputDto transactionListenerInputDto,
			TransactionListenerOutputDto transactionListenerOutputDto, TransactionScenarioWorkspaceDto workspace) {
		try {
			if(null!=transactionListenerInputDto&&null!=transactionListenerInputDto.getIsGiftFlag()&&transactionListenerInputDto.getIsGiftFlag().equalsIgnoreCase("N")) {
				SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_cluster_allocation");
				SqlParameterSource in = new MapSqlParameterSource().addValue("user_id", transactionListenerInputDto.getUserId()).addValue("no_of_SGC", commonUtil.stringToInteger(transactionListenerInputDto.getNoOfSGC())).addValue("transaction_ref_no", transactionListenerInputDto.getTransactionRefNo()).addValue("transaction_timestamp", commonUtil.stringToSqlTimestamp(transactionListenerInputDto.getTransactionTimestamp())).addValue("contract_start_date", commonUtil.stringToSqlDate(transactionListenerInputDto.getContractStartDate())).addValue("contract_period_in_year", commonUtil.stringToInteger(transactionListenerInputDto.getContractPeriod())).addValue("country_of_contract", transactionListenerInputDto.getCountryOfContract());
				Map<String, Object> out = jdbcCall.execute(in);
				System.out.println(out);
				transactionListenerOutputDto.setStatus(out.get("status").toString());
				transactionListenerOutputDto.setDescription(out.get("description").toString());
				workspace.setContractId(out.get("contract_id").toString());
			}
			else if(null!=transactionListenerInputDto&&null!=transactionListenerInputDto.getIsGiftFlag()&&null!=transactionListenerInputDto.getNewPurchase()&&transactionListenerInputDto.getIsGiftFlag().equalsIgnoreCase("Y")&&transactionListenerInputDto.getNewPurchase().equalsIgnoreCase("Y")) {
				SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_gift_giver_scenario1");
				SqlParameterSource in = new MapSqlParameterSource().addValue("transaction_ref_no", transactionListenerInputDto.getTransactionRefNo()).addValue("gift_giver_id", transactionListenerInputDto.getGiftGiverId()).addValue("gift_taker_id", transactionListenerInputDto.getGiftTakerId()).addValue("req_scg", commonUtil.stringToInteger(transactionListenerInputDto.getNoOfSGC())).addValue("new_purchase", transactionListenerInputDto.getNewPurchase()).addValue("transaction_timestamp", commonUtil.stringToSqlTimestamp(transactionListenerInputDto.getTransactionTimestamp())).addValue("contract_start_date", commonUtil.stringToSqlDate(transactionListenerInputDto.getContractStartDate())).addValue("contract_period_in_year", transactionListenerInputDto.getContractPeriod()).addValue("country_of_contract", transactionListenerInputDto.getCountryOfContract());
				Map<String, Object> out = jdbcCall.execute(in);
				System.out.println(out);
				transactionListenerOutputDto.setStatus(out.get("status").toString());
				transactionListenerOutputDto.setDescription(out.get("description").toString());
				transactionListenerOutputDto.setReferralCode(out.get("referral_code_var").toString());
			}
			else if(null!=transactionListenerInputDto&&null!=transactionListenerInputDto.getIsGiftFlag()&&null!=transactionListenerInputDto.getNewPurchase()&&transactionListenerInputDto.getIsGiftFlag().equalsIgnoreCase("Y")&&transactionListenerInputDto.getNewPurchase().equalsIgnoreCase("N")) {
				SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_gift_giver_scenario2");
				SqlParameterSource in = new MapSqlParameterSource().addValue("transaction_ref_no", transactionListenerInputDto.getTransactionRefNo()).addValue("user_id", transactionListenerInputDto.getUserId()).addValue("gift_giver_id", transactionListenerInputDto.getGiftGiverId()).addValue("gift_taker_id", transactionListenerInputDto.getGiftTakerId()).addValue("req_scg", commonUtil.stringToInteger(transactionListenerInputDto.getNoOfSGC())).addValue("new_purchase", transactionListenerInputDto.getNewPurchase()).addValue("contract_period", transactionListenerInputDto.getContractPeriod()).addValue("country_of_contract", transactionListenerInputDto.getCountryOfContract()).addValue("transaction_timestamp", commonUtil.stringToSqlTimestamp(transactionListenerInputDto.getTransactionTimestamp()));
				Map<String, Object> out = jdbcCall.execute(in);
				System.out.println(out);
				transactionListenerOutputDto.setStatus(out.get("status").toString());
				transactionListenerOutputDto.setDescription(out.get("description").toString());
				transactionListenerOutputDto.setReferralCode(out.get("referral_code_var").toString());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public LockAvailableClusterOutputDto lockPersonalCluster(LockAvailableClusterInputDto lockAvailableClusterInputDto,
			LockAvailableClusterOutputDto lockAvailableClusterOutputDto) {
		try {
			SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_personal_lock_cluster");
			SqlParameterSource in = new MapSqlParameterSource().addValue("user_id", lockAvailableClusterInputDto.getUserId()).addValue("req_sgc", lockAvailableClusterInputDto.getRequestedSgc());
			Map<String, Object> out = jdbcCall.execute(in);
			System.out.println(out);
			lockAvailableClusterOutputDto.setStatus(out.get("status").toString());
			lockAvailableClusterOutputDto.setDescription(out.get("description").toString());
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return lockAvailableClusterOutputDto;
	}

	@Override
	public TakerGiftingScenarioOutputDto takerGiftingScenario(TakerGiftingScenarioInputDto takerGiftingScenarioInputDto,
			TakerGiftingScenarioOutputDto takerGiftingScenarioOutputDto, TransactionScenarioWorkspaceDto workspace) {
		try {
			/*if(null!=takerGiftingScenarioInputDto&&null!=takerGiftingScenarioInputDto.getScenario()&&takerGiftingScenarioInputDto.getScenario().equalsIgnoreCase("scenario1")) {
				SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_gift_taker_scenario1");
				SqlParameterSource in = new MapSqlParameterSource().addValue("gift_giver_id", takerGiftingScenarioInputDto.getGiftGiverId()).addValue("gift_taker_id", takerGiftingScenarioInputDto.getGiftTakerId()).addValue("req_scg", takerGiftingScenarioInputDto.getReqScg()).addValue("conract_period", takerGiftingScenarioInputDto.getContractPeriod()).addValue("referal_code", takerGiftingScenarioInputDto.getReferralCode()).addValue("gift_status", takerGiftingScenarioInputDto.getGiftStatus());
				Map<String, Object> out = jdbcCall.execute(in);
				System.out.println(out);
				takerGiftingScenarioOutputDto.setStatus(out.get("status").toString());
				takerGiftingScenarioOutputDto.setDescription(out.get("description").toString());
				takerGiftingScenarioOutputDto.setContractId(out.get("contract_id").toString());
				takerGiftingScenarioOutputDto.setDoPost(true);
			}
			else if(null!=takerGiftingScenarioInputDto&&null!=takerGiftingScenarioInputDto.getScenario()&&takerGiftingScenarioInputDto.getScenario().equalsIgnoreCase("scenario2")) {
				SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_gift_taker_scenario2");
				SqlParameterSource in = new MapSqlParameterSource().addValue("gift_giver_id", takerGiftingScenarioInputDto.getGiftGiverId()).addValue("gift_taker_id", takerGiftingScenarioInputDto.getGiftTakerId()).addValue("req_scg", takerGiftingScenarioInputDto.getReqScg()).addValue("contract_period", takerGiftingScenarioInputDto.getContractPeriod()).addValue("referral_code", takerGiftingScenarioInputDto.getReferralCode()).addValue("gift_status", takerGiftingScenarioInputDto.getGiftStatus()).addValue("country_of_contract", takerGiftingScenarioInputDto.getCountryOfContract()).addValue("transaction_ref_no", takerGiftingScenarioInputDto.getTransactionRefNo()).addValue("transaction_timestamp", commonUtil.stringToSqlTimestamp(takerGiftingScenarioInputDto.getTransactionTimestamp())).addValue("contract_start_date", takerGiftingScenarioInputDto.getContractStartDate());
				Map<String, Object> out = jdbcCall.execute(in);
				System.out.println(out);
				takerGiftingScenarioOutputDto.setStatus(out.get("status").toString());
				takerGiftingScenarioOutputDto.setDescription(out.get("description").toString());
				ContractMasterModel contract = contractMasterModelDao.getNewlyCreatedContractForTaker(takerGiftingScenarioInputDto.getReferralCode());
				takerGiftingScenarioOutputDto.setContractId(contract.getContractId());
				if(takerGiftingScenarioOutputDto.getDescription().equalsIgnoreCase("Gift Accepted")) {
					takerGiftingScenarioOutputDto.setDoPost(true);
				}
				else {
					takerGiftingScenarioOutputDto.setDoPost(false);
				}
			}*/
			SimpleJdbcCall jdbcCall= new SimpleJdbcCall(dataSource).withProcedureName("api_gift_taker");
			SqlParameterSource in = new MapSqlParameterSource().addValue("gift_giver_id", takerGiftingScenarioInputDto.getGiftGiverId()).addValue("gift_taker_id", takerGiftingScenarioInputDto.getGiftTakerId()).addValue("gifted_scg", takerGiftingScenarioInputDto.getReqScg()).addValue("referral_code", takerGiftingScenarioInputDto.getReferralCode()).addValue("gift_action", takerGiftingScenarioInputDto.getGiftStatus());
			Map<String, Object> out = jdbcCall.execute(in);
			System.out.println(out);
			if(null!=out.get("status")) {
				takerGiftingScenarioOutputDto.setStatus(out.get("status").toString());
			}
			if(null!=out.get("description")) {
				takerGiftingScenarioOutputDto.setDescription(out.get("description").toString());
			}
			if(null!=out.get("gift_type")) {
				takerGiftingScenarioOutputDto.setGiftType(out.get("gift_type").toString());
			}
			if(null!=out.get("impacted_contract_ids")) {
				takerGiftingScenarioOutputDto.setImpactedContracts(out.get("impacted_contract_ids").toString());
			}
			if(takerGiftingScenarioInputDto.getGiftStatus().equalsIgnoreCase("REJECT")&&takerGiftingScenarioOutputDto.getGiftType().equalsIgnoreCase("scenario2")) {
				workspace.setDoPost(false);
			}
			else {
				workspace.setDoPost(true);
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return takerGiftingScenarioOutputDto;
	}
	
}
