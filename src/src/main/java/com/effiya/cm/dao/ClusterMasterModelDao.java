package com.effiya.cm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.effiya.cm.model.ClusterMasterModel;

@Repository
public interface ClusterMasterModelDao extends JpaRepository<ClusterMasterModel, Integer>, JpaSpecificationExecutor<ClusterMasterModel>{

	List<ClusterMasterModel> findAllByForestId(String forestId);

	List<ClusterMasterModel> findAllByContractId(String contractId);

	

}
