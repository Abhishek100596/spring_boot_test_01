package com.effiya.jbehave.steps;

import static org.junit.Assert.assertNotEquals;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.steps.Steps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import com.effiya.cm.controller.ClusterMgmtController;
import com.effiya.cm.dto.AvailableClusterDto;
import com.effiya.cm.dto.LockAvailableClusterInputDto;
import com.effiya.cm.dto.LockAvailableClusterOutputDto;
import com.effiya.cm.dto.TakerGiftingScenarioInputDto;
import com.effiya.cm.dto.TakerGiftingScenarioOutputDto;
import com.effiya.cm.dto.TransactionListenerInputDto;
import com.effiya.cm.dto.TransactionListenerOutputDto;
import com.effiya.cm.service.ClusterMgmtService;

public class Scenario1Transaction extends Steps{
	
	@Autowired
	ClusterMgmtController controller;
	
	@Autowired
	ClusterMgmtService service;
	
	private int previousAvailClusters;
	private int finalAvailClusters;
	private String userId = "a-1";
	private String giverId = "a-1";
	private String takerId = "b-1";
	private String paymentRef = "tr123";
	private String transactionDttm = "2018-01-01 14:40:34.59";
	private String contractStartDate = "2018-01-01";
	private int reqScg = 5;
	private String scenario = "scenario1";
	private String giftAction = "ACCEPT";
	private String newPurchase = "Y";
	private String isGiftFlag = "Y";
	private String contractPeriod = "10";
	private String countryOfContract = "UAE";
	private String referralCode;
	
	
	@Given("previous available clusters")
	void getPreviousGlobalAvailClusters() {
		try {
			AvailableClusterDto availableClusterDto = new AvailableClusterDto();
			service.availableCluster(availableClusterDto);
			previousAvailClusters = availableClusterDto.getAvailableCluster();
			System.out.println(previousAvailClusters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Then("lock clusters")
	void lockAvailClusters() {
		try {
			LockAvailableClusterInputDto lockAvailableClusterInputDto = new LockAvailableClusterInputDto();
			lockAvailableClusterInputDto.setRequestedSgc(reqScg);
			lockAvailableClusterInputDto.setScenario(scenario);
			lockAvailableClusterInputDto.setUserId(userId);
			LockAvailableClusterOutputDto lockAvailableClusterOutputDto = new LockAvailableClusterOutputDto();
			service.lockAvailableClusterStoredProcess(lockAvailableClusterInputDto, lockAvailableClusterOutputDto);
			System.out.println(lockAvailableClusterOutputDto.getStatus());
			assertNotEquals(lockAvailableClusterOutputDto.getStatus(), null);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Then("transaction s1")
	void transactionScenario() {
		try {
			TransactionListenerInputDto transactionListenerInputDto = new TransactionListenerInputDto();
			transactionListenerInputDto.setUserId(userId);
			transactionListenerInputDto.setContractPeriod(contractPeriod);
			transactionListenerInputDto.setContractStartDate(contractStartDate);
			transactionListenerInputDto.setCountryOfContract(countryOfContract);
			transactionListenerInputDto.setGiftGiverId(giverId);
			transactionListenerInputDto.setGiftTakerId(takerId);
			transactionListenerInputDto.setIsGiftFlag(isGiftFlag);
			transactionListenerInputDto.setNewPurchase(newPurchase);
			transactionListenerInputDto.setNoOfSGC(String.valueOf(reqScg));
			transactionListenerInputDto.setTransactionRefNo(paymentRef);
			transactionListenerInputDto.setTransactionTimestamp(transactionDttm);
			TransactionListenerOutputDto transactionListenerOutputDto = new TransactionListenerOutputDto();
			service.transactionListenerStoredProcess(transactionListenerInputDto, transactionListenerOutputDto);
			System.out.println(transactionListenerOutputDto.getStatus());
			referralCode = transactionListenerOutputDto.getReferralCode();
			assertNotEquals(transactionListenerOutputDto.getStatus(), "false");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Then("taker gifting s1")
	void takerGiftingScenario() {
		try {
			TakerGiftingScenarioInputDto takerGiftingScenarioInputDto =new TakerGiftingScenarioInputDto();
			takerGiftingScenarioInputDto.setGiftGiverId(giverId);
			takerGiftingScenarioInputDto.setGiftStatus(giftAction);
			takerGiftingScenarioInputDto.setGiftTakerId(takerId);
			takerGiftingScenarioInputDto.setReferralCode(referralCode);
			takerGiftingScenarioInputDto.setReqScg(String.valueOf(reqScg));
			TakerGiftingScenarioOutputDto takerGiftingScenarioOutputDto = new TakerGiftingScenarioOutputDto();
			service.takerGiftingScenario(takerGiftingScenarioInputDto, takerGiftingScenarioOutputDto);
			System.out.println(takerGiftingScenarioOutputDto.getStatus());
			assertNotEquals(takerGiftingScenarioOutputDto.getStatus(), "false");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Then("final available clusters")
	void getFinalGlobalAvailClusters() {
		try {
			AvailableClusterDto availableClusterDto = new AvailableClusterDto();
			service.availableCluster(availableClusterDto);
			finalAvailClusters = availableClusterDto.getAvailableCluster();
			System.out.println(finalAvailClusters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
