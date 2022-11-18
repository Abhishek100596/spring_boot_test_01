package com.effiya.cm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.effiya.cm.model.GlobalAvailableClustersModel;

@Repository
public interface GlobalAvailableClustersJpa extends JpaRepository<GlobalAvailableClustersModel, Integer>, JpaSpecificationExecutor<GlobalAvailableClustersModel>{

}
