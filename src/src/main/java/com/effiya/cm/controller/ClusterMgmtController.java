package com.effiya.cm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.effiya.cm.dto.ActiveUserInputDto;
import com.effiya.cm.dto.AddClusterInputDto;
import com.effiya.cm.dto.AddClusterOutputDto;
import com.effiya.cm.dto.AvailableClusterDto;
import com.effiya.cm.dto.ContractDetail;
import com.effiya.cm.dto.DeleteContractInputDto;
import com.effiya.cm.dto.DeleteContractOutputDto;
import com.effiya.cm.dto.EligibleGiftingInputDto;
import com.effiya.cm.dto.EligibleGiftingOutputDto;
import com.effiya.cm.dto.FilterQueryDto;
import com.effiya.cm.dto.FilteredResultDto;
import com.effiya.cm.dto.LimitClusterInputDto;
import com.effiya.cm.dto.LimitClusterOutputDto;
import com.effiya.cm.dto.LockAvailableClusterInputDto;
import com.effiya.cm.dto.LockAvailableClusterOutputDto;
import com.effiya.cm.dto.QueryContractTransactionsInputDto;
import com.effiya.cm.dto.QueryContractTransactionsOutputDto;
import com.effiya.cm.dto.QueryContractsInfo;
import com.effiya.cm.dto.ResponseDto;
import com.effiya.cm.dto.RevokeUserInputDto;
import com.effiya.cm.dto.TakerGiftingScenarioInputDto;
import com.effiya.cm.dto.TakerGiftingScenarioOutputDto;
import com.effiya.cm.dto.TransactionListenerInputDto;
import com.effiya.cm.dto.TransactionListenerOutputDto;
import com.effiya.cm.dto.UserIdDto;
import com.effiya.cm.response.RestResponse;
import com.effiya.cm.service.ClusterMgmtService;
/**
 * @author Abhishek Kumar Singh
 */

@RestController
@RequestMapping("/clustermgmt/")
public class ClusterMgmtController {
	
	private static Logger logger = LoggerFactory.getLogger(ClusterMgmtController.class);
	
	@Autowired
	ClusterMgmtService clusterMgmtService;

	@RequestMapping(value = "create/user", method = RequestMethod.POST)
	public RestResponse userRegistration(@RequestBody UserIdDto userIdDto) throws IOException{
		ResponseDto responseDto=new ResponseDto();
		try {
			responseDto = clusterMgmtService.createUser(userIdDto,responseDto);
			return RestResponse.ok(responseDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "revoke/user", method = RequestMethod.POST)
	public RestResponse revokeUser(@RequestBody RevokeUserInputDto revokeUserInputDto) throws IOException{
		ResponseDto responseDto=new ResponseDto();
		try {
			responseDto = clusterMgmtService.revokeUser(revokeUserInputDto,responseDto);
			return RestResponse.ok(responseDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "activate/user", method = RequestMethod.POST)
	public RestResponse activateUser(@RequestBody ActiveUserInputDto activeUserInputDto) throws IOException{
		ResponseDto responseDto=new ResponseDto();
		try {
			responseDto = clusterMgmtService.activateUser(activeUserInputDto,responseDto);
			return RestResponse.ok(responseDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "delete/contract", method = RequestMethod.POST)
	public RestResponse deleteUser(@RequestBody DeleteContractInputDto deleteContractInputDto) throws IOException{
		DeleteContractOutputDto deleteContractOutputDto = new DeleteContractOutputDto();
		try {
			deleteContractOutputDto = clusterMgmtService.deleteContract(deleteContractInputDto,deleteContractOutputDto);
			return RestResponse.ok(deleteContractOutputDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "available/clusters", method = RequestMethod.GET)
	public RestResponse availableCluster() throws IOException{
		AvailableClusterDto availableClusterDto=new AvailableClusterDto();
		try {
			clusterMgmtService.availableCluster(availableClusterDto);
			return RestResponse.ok(availableClusterDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "lock/available/cluster/stored/process", method = RequestMethod.POST)
	public RestResponse lockAvailableClusterStoredProcess(@RequestBody LockAvailableClusterInputDto lockAvailableClusterInputDto) {
		LockAvailableClusterOutputDto lockAvailableClusterOutputDto = new LockAvailableClusterOutputDto();
		try {
			clusterMgmtService.lockAvailableClusterStoredProcess(lockAvailableClusterInputDto,lockAvailableClusterOutputDto);
			return RestResponse.ok(lockAvailableClusterOutputDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "eligible/gifting/stored/process", method = RequestMethod.POST)
	public RestResponse eligibleGiftingStoredProcess(@RequestBody EligibleGiftingInputDto eligibleGiftingInputDto) {
		EligibleGiftingOutputDto eligibleGiftingOutputDto=new EligibleGiftingOutputDto();
		try {
			clusterMgmtService.eligibleGiftingStoredProcess(eligibleGiftingInputDto,eligibleGiftingOutputDto);
			return RestResponse.ok(eligibleGiftingOutputDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "limit/cluster/stored/process", method = RequestMethod.POST)
	public RestResponse limitClusterStoredProcess(@RequestBody LimitClusterInputDto limitClusterInputDto) {
		LimitClusterOutputDto limitClusterOutputDto=new LimitClusterOutputDto();
		try {
			clusterMgmtService.limitClusterStoredProcess(limitClusterInputDto,limitClusterOutputDto);
			return RestResponse.ok(limitClusterOutputDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "transaction/listener/stored/process", method = RequestMethod.POST)
	public RestResponse transactionListenerStoredProcess(@RequestBody TransactionListenerInputDto transactionListenerInputDto) {
		TransactionListenerOutputDto transactionListenerOutputDto = new TransactionListenerOutputDto();
		try {
			clusterMgmtService.transactionListenerStoredProcess(transactionListenerInputDto,transactionListenerOutputDto);
			return RestResponse.ok(transactionListenerOutputDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "query/contract/transactions", method = RequestMethod.POST)
	public RestResponse queryContractTransactions(@RequestBody QueryContractTransactionsInputDto queryContractTransactionsInputDto) {
		QueryContractsInfo queryContractsInfo = new QueryContractsInfo();
		List<ContractDetail> contractDetailList = new ArrayList<>();
		QueryContractTransactionsOutputDto queryContractTransactionsOutputDto = new QueryContractTransactionsOutputDto();
		try {
			queryContractTransactionsOutputDto = clusterMgmtService.queryContractTransactions(queryContractTransactionsInputDto,queryContractTransactionsOutputDto);
			for(ContractDetail detail : queryContractTransactionsOutputDto.getMessage().getContractDetails()) {
				contractDetailList.add(detail);
			}
			queryContractsInfo.setContractDetailList(contractDetailList);
			return RestResponse.ok(queryContractsInfo);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "query/last-100/global/contract/transactions", method = RequestMethod.GET)
	public RestResponse queryContractTransactions() {
		QueryContractsInfo queryContractsInfo = new QueryContractsInfo();
		List<ContractDetail> contractDetailList = new ArrayList<>();
		QueryContractTransactionsOutputDto queryContractTransactionsOutputDto = new QueryContractTransactionsOutputDto();
		try {
			queryContractTransactionsOutputDto = clusterMgmtService.queryLast100GlobalContractTransactions(queryContractTransactionsOutputDto);
			for(ContractDetail detail : queryContractTransactionsOutputDto.getMessage().getContractsDetails()) {
				contractDetailList.add(detail);
			}
			queryContractsInfo.setContractDetailList(contractDetailList);
			return RestResponse.ok(queryContractsInfo);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "lock/cluster", method = RequestMethod.POST)
	public RestResponse lockCluster(@RequestBody LockAvailableClusterInputDto lockAvailableClusterInputDto) {
		LockAvailableClusterOutputDto lockAvailableClusterOutputDto = new LockAvailableClusterOutputDto();
		try {
			lockAvailableClusterOutputDto = clusterMgmtService.lockCluster(lockAvailableClusterInputDto, lockAvailableClusterOutputDto);
			return RestResponse.ok(lockAvailableClusterOutputDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "add/cluster", method = RequestMethod.POST)
	public RestResponse addCluster(@RequestBody AddClusterInputDto addClusterInputDto) {
		AddClusterOutputDto addClusterOutputDto = new AddClusterOutputDto();
		try {
			addClusterOutputDto = clusterMgmtService.addCluster(addClusterInputDto, addClusterOutputDto);
			return RestResponse.ok(addClusterOutputDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "taker/gifting/scenario", method = RequestMethod.POST)
	public RestResponse takerGiftingScenario(@RequestBody TakerGiftingScenarioInputDto takerGiftingScenarioInputDto) {
		TakerGiftingScenarioOutputDto takerGiftingScenarioOutputDto = new TakerGiftingScenarioOutputDto();
		try {
			takerGiftingScenarioOutputDto = clusterMgmtService.takerGiftingScenario(takerGiftingScenarioInputDto, takerGiftingScenarioOutputDto);
			return RestResponse.ok(takerGiftingScenarioOutputDto);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	@RequestMapping(value = "query/contracts/by/user", method = RequestMethod.POST)
	public RestResponse queryContractsByFilter(@RequestBody FilterQueryDto filterQueryDto) {
		FilteredResultDto resultList = new FilteredResultDto();
		try {
			resultList = clusterMgmtService.queryContractsByFilter(filterQueryDto, resultList);
			return RestResponse.ok(resultList);
		}
		catch(Exception e){
			logger.error("Exception :",e);
			return RestResponse.fail();
		}
	}
	
	public int add(int x, int y) {
		return(x+y);
	}
}
