package com.effiya.cm.service;

import com.effiya.cm.dto.ActiveUserInputDto;
import com.effiya.cm.dto.AddClusterInputDto;
import com.effiya.cm.dto.AddClusterOutputDto;
import com.effiya.cm.dto.AvailableClusterDto;
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
import com.effiya.cm.dto.ResponseDto;
import com.effiya.cm.dto.RevokeUserInputDto;
import com.effiya.cm.dto.TakerGiftingScenarioInputDto;
import com.effiya.cm.dto.TakerGiftingScenarioOutputDto;
import com.effiya.cm.dto.TransactionListenerInputDto;
import com.effiya.cm.dto.TransactionListenerOutputDto;
import com.effiya.cm.dto.UserIdDto;

public interface ClusterMgmtService {

	ResponseDto createUser(UserIdDto userIdDto, ResponseDto responseDto);

	ResponseDto revokeUser(RevokeUserInputDto revokeUserInputDto, ResponseDto responseDto);

	ResponseDto activateUser(ActiveUserInputDto activeUserInputDto, ResponseDto responseDto);

	void availableCluster(AvailableClusterDto availableClusterDto);

	void lockAvailableClusterStoredProcess(LockAvailableClusterInputDto lockAvailableClusterInputDto,
			LockAvailableClusterOutputDto lockAvailableClusterOutputDto);

	void eligibleGiftingStoredProcess(EligibleGiftingInputDto eligibleGiftingInputDto,
			EligibleGiftingOutputDto eligibleGiftingOutputDto);

	void limitClusterStoredProcess(LimitClusterInputDto limitClusterInputDto,
			LimitClusterOutputDto limitClusterOutputDto);

	DeleteContractOutputDto deleteContract(DeleteContractInputDto deleteContractInputDto, DeleteContractOutputDto deleteContractOutputDto);

	void transactionListenerStoredProcess(TransactionListenerInputDto transactionListenerInputDto,
			TransactionListenerOutputDto transactionListenerOutputDto);

	QueryContractTransactionsOutputDto queryContractTransactions(QueryContractTransactionsInputDto queryContractTransactionsInputDto,
			QueryContractTransactionsOutputDto queryContractTransactionsOutputDto);

	QueryContractTransactionsOutputDto queryLast100GlobalContractTransactions(QueryContractTransactionsOutputDto queryContractTransactionsOutputDto);

	LockAvailableClusterOutputDto lockCluster(LockAvailableClusterInputDto lockAvailableClusterInputDto,
			LockAvailableClusterOutputDto lockAvailableClusterOutputDto);

	AddClusterOutputDto addCluster(AddClusterInputDto addClusterInputDto, AddClusterOutputDto addClusterOutputDto);

	TakerGiftingScenarioOutputDto takerGiftingScenario(TakerGiftingScenarioInputDto takerGiftingScenarioInputDto,
			TakerGiftingScenarioOutputDto takerGiftingScenarioOutputDto);

	FilteredResultDto queryContractsByFilter(FilterQueryDto filterQueryDto, FilteredResultDto resultList);

}
