package com.effiya.cm.dao;

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

/**
 * @author Abhishek Kumar Singh
 */

public interface StoredProcessDao {

	void deleteContractStoredProcess(DeleteContractInputDto deleteContractInputDto, DeleteContractOutputDto deleteContractOutputDto);

	void lockAvailableClusterStoredProcess(LockAvailableClusterInputDto lockAvailableClusterInputDto,
			LockAvailableClusterOutputDto lockAvailableClusterOutputDto);

	void eligibleGiftingStoredProcess(EligibleGiftingInputDto eligibleGiftingInputDto,
			EligibleGiftingOutputDto eligibleGiftingOutputDto);

	void limitClusterStoredProcess(LimitClusterInputDto limitClusterInputDto,
			LimitClusterOutputDto limitClusterOutputDto);

	void transactionListenerStoredProcess(TransactionListenerInputDto transactionListenerInputDto,
			TransactionListenerOutputDto transactionListenerOutputDto, TransactionScenarioWorkspaceDto workspace);

	LockAvailableClusterOutputDto lockPersonalCluster(LockAvailableClusterInputDto lockAvailableClusterInputDto,
			LockAvailableClusterOutputDto lockAvailableClusterOutputDto);

	TakerGiftingScenarioOutputDto takerGiftingScenario(TakerGiftingScenarioInputDto takerGiftingScenarioInputDto,
			TakerGiftingScenarioOutputDto takerGiftingScenarioOutputDto, TransactionScenarioWorkspaceDto workspace);

}
