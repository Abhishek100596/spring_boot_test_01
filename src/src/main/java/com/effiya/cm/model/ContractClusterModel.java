package com.effiya.cm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Abhishek Kumar Singh
 */

@Entity
@Table(name = "CONTRACT_CLUSTERS")
public class ContractClusterModel implements Serializable {	

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="contract_clusters_sno")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="contractClustersSeq")
	@SequenceGenerator(name="contractClustersSeq", sequenceName= "contractClustersSeq",allocationSize=1)
	private Integer contractClustersSno;
	
	@Column(name = "contract_id")
    private Integer contractId;
 
    @Column(name = "cluster_id")
    private Integer clusterId;

	public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
