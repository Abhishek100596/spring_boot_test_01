package com.effiya.cm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.effiya.cm.model.UserAssetEventsModel;

@Repository
public interface UserAssetEventsJpa extends JpaRepository<UserAssetEventsModel, Integer>, JpaSpecificationExecutor<UserAssetEventsModel>{

	UserAssetEventsModel findByEventImpactedIdAndEventOn(String userId, String string);

}
