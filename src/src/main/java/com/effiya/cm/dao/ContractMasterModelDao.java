package com.effiya.cm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.effiya.cm.model.ContractMasterModel;

@Repository
public interface ContractMasterModelDao extends JpaRepository<ContractMasterModel, Integer>, JpaSpecificationExecutor<ContractMasterModel>{

	ContractMasterModel findByContractId(String key);

	@Query("select c from ContractMasterModel c where c.referralCode = :referralCode and c.userId = c.giftTakerId")
	ContractMasterModel getNewlyCreatedContractForTaker(@Param("referralCode") String referralCode);

	List<ContractMasterModel> findAllByReferralCode(String referralCode);

	List<ContractMasterModel> findAllByUserId(String userId);

}
