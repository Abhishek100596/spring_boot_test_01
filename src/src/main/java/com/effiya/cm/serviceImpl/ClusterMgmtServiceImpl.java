package com.effiya.cm.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.effiya.cm.dao.ClusterMasterModelDao;
import com.effiya.cm.dao.ContractMasterModelDao;
import com.effiya.cm.dao.GlobalAvailableClustersJpa;
import com.effiya.cm.dao.StoredProcessDao;
import com.effiya.cm.dao.UserAssetEventsJpa;
import com.effiya.cm.dto.ActiveUserInputDto;
import com.effiya.cm.dto.AddClusterInputDto;
import com.effiya.cm.dto.AddClusterOutputDto;
import com.effiya.cm.dto.AvailableClusterDto;
import com.effiya.cm.dto.ClusterDetails;
import com.effiya.cm.dto.ContractDetails;
import com.effiya.cm.dto.ContractInfo;
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
import com.effiya.cm.dto.RestTemplateResponseDto;
import com.effiya.cm.dto.RevokeUserInputDto;
import com.effiya.cm.dto.TakerGiftingScenarioInputDto;
import com.effiya.cm.dto.TakerGiftingScenarioOutputDto;
import com.effiya.cm.dto.TransactionListenerInputDto;
import com.effiya.cm.dto.TransactionListenerOutputDto;
import com.effiya.cm.dto.TransactionScenarioWorkspaceDto;
import com.effiya.cm.dto.UserDto;
import com.effiya.cm.dto.UserIdDto;
import com.effiya.cm.helper.ClusterMgmtHelper;
import com.effiya.cm.model.ClusterMasterModel;
import com.effiya.cm.model.ContractMasterModel;
import com.effiya.cm.model.GlobalAvailableClustersModel;
import com.effiya.cm.model.UserAssetEventsModel;
import com.effiya.cm.service.ClusterMgmtService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class ClusterMgmtServiceImpl implements ClusterMgmtService{
	
	@Autowired
	UserAssetEventsJpa userAssetEventsJpa;
	
	@Autowired
	GlobalAvailableClustersJpa globalAvailableClustersJpa;
	
	@Autowired
	ClusterMgmtHelper clusterMgmtHelper;
	
	@Autowired
	StoredProcessDao storedProcessDao;
	
	@Autowired
	ClusterMasterModelDao clusterMasterModelDao;
	
	@Autowired
	ContractMasterModelDao contractMasterModelDao;
	
	@Value("${user.login.url}")
	private String userLoginUrl;
	
	@Value("${add.user.url}")
	private String addUserUrl;
	
	@Value("${revoke.user.url}")
	private String revokeUserUrl;
	
	@Value("${activate.user.url}")
	private String activateUserUrl;
	
	@Value("${delete.contract.url}")
	private String deleteContractUrl;
	
	@Value("${query.contract.transactions.url}")
	private String queryContractTransactionsUrl;
	
	@Value("${query.last100.global.contract.transactions.url}")
	private String queryLast100GlobalContractTransactionsUrl;
	
	@Value("${create.contract.url}")
	private String createContractUrl;
	
	@Value("${edit.contract.url}")
	private String editContractUrl;
	
	@Value("${add.cluster.url}")
	private String addClusterUrl;
	
	@Value("${orgName}")
	private String orgName;
	
	@Value("${userName1}")
	private String userName;
	
	@Value("${is_active}")
	private String is_active;
	
	@Value("${searchTerm}")
	private String searchTerm;
	
	@Value("${searchType}")
	private String searchType;

	@Override
	public ResponseDto createUser(UserIdDto userIdDto, ResponseDto responseDto) {
		UserAssetEventsModel userAssetEventsModel=new UserAssetEventsModel();
		clusterMgmtHelper.setValueInUserAssetModel(userIdDto,userAssetEventsModel);
		userAssetEventsJpa.save(userAssetEventsModel);
        RestTemplate restTemplate = new RestTemplate();
        Map m = new HashMap<>();
        m.put("orgName", orgName);
        m.put("userName", userName);
        UserDto userDto=new UserDto();
        userDto.setIs_active(is_active);
        userDto.setUser_id(userIdDto.getUserId());
        m.put("User", userDto);
        HttpEntity<Object> postReq = new HttpEntity(m);
        ObjectMapper mapper = new ObjectMapper();
		String usersObject;
        try {
	        ResponseDto loginResponse = loginUser(userIdDto.getUserId()); 
	        if(null==loginResponse.getSuccess()) {
	        	responseDto.setSuccess("false");
	        	responseDto.setMessage("Error while calling login URL");
	        }
	        else if(loginResponse.getSuccess().equalsIgnoreCase("true")) {
	        	responseDto.setSuccess("false");
	        	responseDto.setMessage("User already exist");
	        }
	        else if(loginResponse.getSuccess().equalsIgnoreCase("false")&&loginResponse.getMessage().equalsIgnoreCase("User does not exist")) {
	        	ResponseEntity<Object> response = restTemplate.postForEntity(addUserUrl, postReq, Object.class);
	        	usersObject = mapper.writeValueAsString(response.getBody());
	        	responseDto = mapper.readValue(usersObject, new TypeReference<ResponseDto>(){});
	        }
	        else if(loginResponse.getSuccess().equalsIgnoreCase("false")&&loginResponse.getMessage().equalsIgnoreCase("User is inactive")){
	        	responseDto.setSuccess("false");
	        	responseDto.setMessage("User already exist");
	        }
  		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseDto;
	}

	private ResponseDto loginUser(String userId) {
		ResponseDto loginResponse = new ResponseDto();
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		String usersObject;
        Map m = new HashMap<>();
        m.put("orgName", orgName);
        m.put("userName", userName);
        m.put("userId", userId);
        HttpEntity<Object> postReq = new HttpEntity(m);
        try {
        	ResponseEntity<Object> response = restTemplate.postForEntity(userLoginUrl, postReq, Object.class);
        	usersObject = mapper.writeValueAsString(response.getBody());
        	loginResponse = mapper.readValue(usersObject, new TypeReference<ResponseDto>(){});
        }
        catch(Exception ex) {
        	ex.printStackTrace();
        }
        return loginResponse;
	}

	@Override
	public ResponseDto revokeUser(RevokeUserInputDto revokeUserInputDto, ResponseDto responseDto) {
		UserAssetEventsModel userAssetEventsModel = new UserAssetEventsModel();
		clusterMgmtHelper.setRevokeInModel(userAssetEventsModel,revokeUserInputDto);
		userAssetEventsJpa.save(userAssetEventsModel);
		
		RestTemplate restTemplate = new RestTemplate();
        Map m = new HashMap<>();
        m.put("orgName", orgName);
        m.put("userName", userName);
        m.put("userId", revokeUserInputDto.getUserId());
        HttpEntity<Object> postReq = new HttpEntity(m);
        ObjectMapper mapper = new ObjectMapper();
		String usersObject;
        try {
	        ResponseDto loginResponse = loginUser(revokeUserInputDto.getUserId()); 
	        if(null==loginResponse.getSuccess()) {
	        	responseDto.setSuccess("false");
	        	responseDto.setMessage("Error while calling login URL");
	        }
	        else if(loginResponse.getSuccess().equalsIgnoreCase("false")&&loginResponse.getMessage().equalsIgnoreCase("User is inactive")) {
	        	responseDto.setSuccess("true");
	        	responseDto.setMessage("User is already revoked");
	        }
	        else if(loginResponse.getSuccess().equalsIgnoreCase("false")&&loginResponse.getMessage().equalsIgnoreCase("User does not exist")) {
	        	responseDto.setSuccess("false");
	        	responseDto.setMessage("User does not exist");
	        }
	        else if(loginResponse.getSuccess().equalsIgnoreCase("true")) {
		        ResponseEntity<Object> response = restTemplate.postForEntity(revokeUserUrl, postReq, Object.class);
		        usersObject = mapper .writeValueAsString(response.getBody());
		        responseDto = mapper.readValue(usersObject, new TypeReference<ResponseDto>(){});
	        }
		} 
        catch (Exception e) {
			e.printStackTrace();
		}
        return responseDto;
	}

	@Override
	public ResponseDto activateUser(ActiveUserInputDto activeUserInputDto, ResponseDto responseDto) {
		UserAssetEventsModel userAssetEventsModel = new UserAssetEventsModel();
		clusterMgmtHelper.setActivateInModel(userAssetEventsModel,activeUserInputDto);
		userAssetEventsJpa.save(userAssetEventsModel);
		
		RestTemplate restTemplate = new RestTemplate();
        Map m = new HashMap<>();
        m.put("orgName", orgName);
        m.put("userName", userName);
        m.put("userId", activeUserInputDto.getUserId());
        HttpEntity<Object> postReq = new HttpEntity(m);
        ObjectMapper mapper = new ObjectMapper();
		String usersObject;
        try {
	        ResponseDto loginResponse = loginUser(activeUserInputDto.getUserId()); 
	        if(null==loginResponse.getSuccess()) {
	        	responseDto.setSuccess("false");
	        	responseDto.setMessage("Error while calling login URL");
	        }
	        else if(loginResponse.getSuccess().equalsIgnoreCase("false")&&loginResponse.getMessage().equalsIgnoreCase("User is inactive")) {
	        	ResponseEntity<Object> response = restTemplate.postForEntity(activateUserUrl, postReq, Object.class);
		        usersObject = mapper .writeValueAsString(response.getBody());
		        responseDto = mapper.readValue(usersObject, new TypeReference<ResponseDto>(){});
	        }
	        else if(loginResponse.getSuccess().equalsIgnoreCase("false")&&loginResponse.getMessage().equalsIgnoreCase("User does not exist")) {
	        	responseDto.setSuccess("false");
	        	responseDto.setMessage("User does not exist");
	        }
	        else if(loginResponse.getSuccess().equalsIgnoreCase("true")) {
	        	responseDto.setSuccess("true");
	        	responseDto.setMessage("User is already active");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
        return responseDto;
	}

	@Override
	public DeleteContractOutputDto deleteContract(DeleteContractInputDto deleteContractInputDto, DeleteContractOutputDto deleteContractOutputDto) {
		UserAssetEventsModel userAssetEventsModel = new UserAssetEventsModel();
		clusterMgmtHelper.setDeleteContractInModel(userAssetEventsModel,deleteContractInputDto);
		userAssetEventsJpa.save(userAssetEventsModel);
		storedProcessDao.deleteContractStoredProcess(deleteContractInputDto,deleteContractOutputDto);
		if(null!=deleteContractOutputDto.getMessage()&&deleteContractOutputDto.getMessage().equalsIgnoreCase("Error executing stored procedure")) {
			return deleteContractOutputDto;
		}
		RestTemplate restTemplate = new RestTemplate();
        Map m = new HashMap<>();
        m.put("orgName", orgName);
        m.put("userName", userName);
        m.put("contractId", deleteContractInputDto.getContractId());
        m.put("deleteReason", deleteContractInputDto.getReason());
        HttpEntity<Object> postReq = new HttpEntity(m);
        ResponseEntity<Object> response = restTemplate.postForEntity(deleteContractUrl, postReq, Object.class);
		ObjectMapper mapper = new ObjectMapper();
		String usersObject;
		try {
			usersObject = mapper .writeValueAsString(response.getBody());
			deleteContractOutputDto = mapper.readValue(usersObject, new TypeReference<DeleteContractOutputDto>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deleteContractOutputDto;
	}

	@Override
	public void availableCluster(AvailableClusterDto availableClusterDto) {
		List<GlobalAvailableClustersModel> globalAvailableClustersModelList=globalAvailableClustersJpa.findAll();
		for(GlobalAvailableClustersModel globalAvailableClustersModel:globalAvailableClustersModelList) {
			availableClusterDto.setAvailableCluster(globalAvailableClustersModel.getCurrentAvailableClusters());
		}
	}

	@Override
	public void lockAvailableClusterStoredProcess(LockAvailableClusterInputDto lockAvailableClusterInputDto,
			LockAvailableClusterOutputDto lockAvailableClusterOutputDto) {
		storedProcessDao.lockAvailableClusterStoredProcess(lockAvailableClusterInputDto,lockAvailableClusterOutputDto);
		
	}

	@Override
	public void eligibleGiftingStoredProcess(EligibleGiftingInputDto eligibleGiftingInputDto,
			EligibleGiftingOutputDto eligibleGiftingOutputDto) {
		storedProcessDao.eligibleGiftingStoredProcess(eligibleGiftingInputDto,eligibleGiftingOutputDto);
		
	}

	@Override
	public void limitClusterStoredProcess(LimitClusterInputDto limitClusterInputDto,
			LimitClusterOutputDto limitClusterOutputDto) {
		storedProcessDao.limitClusterStoredProcess(limitClusterInputDto,limitClusterOutputDto);
		
	}

	@Override
	public void transactionListenerStoredProcess(TransactionListenerInputDto transactionListenerInputDto,
			TransactionListenerOutputDto transactionListenerOutputDto) {
		TransactionScenarioWorkspaceDto workspace = new TransactionScenarioWorkspaceDto();
		if(null!=transactionListenerInputDto&&null==transactionListenerInputDto.getContractPeriod()) {
			transactionListenerInputDto.setContractPeriod("10");
		}
		storedProcessDao.transactionListenerStoredProcess(transactionListenerInputDto, transactionListenerOutputDto, workspace);
		if(null!=transactionListenerInputDto&&null!=transactionListenerInputDto.getIsGiftFlag()&&transactionListenerInputDto.getIsGiftFlag().equalsIgnoreCase("N")) {
			TakerGiftingScenarioOutputDto takerGiftingScenarioOutputDto = new TakerGiftingScenarioOutputDto();
			addContract(workspace.getContractId(), "REJECT", takerGiftingScenarioOutputDto);
		}
	}

	@Override
	public QueryContractTransactionsOutputDto queryContractTransactions(QueryContractTransactionsInputDto queryContractTransactionsInputDto,
			QueryContractTransactionsOutputDto queryContractTransactionsOutputDto) {
		RestTemplate restTemplate = new RestTemplate();
        Map m = new HashMap<>();
        m.put("orgName", orgName);
        m.put("userName", userName);
	    m.put("searchTerm", queryContractTransactionsInputDto.getContractId());
	    m.put("searchType", searchType);
        HttpEntity<Object> postReq = new HttpEntity(m);
        ResponseEntity<Object> response = restTemplate.postForEntity(queryContractTransactionsUrl, postReq, Object.class);
		ObjectMapper mapper = new ObjectMapper();
		String usersObject;
		try {
			usersObject = mapper.writeValueAsString(response.getBody());
			queryContractTransactionsOutputDto = mapper.readValue(usersObject, new TypeReference<QueryContractTransactionsOutputDto>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryContractTransactionsOutputDto;
	}

	@Override
	public QueryContractTransactionsOutputDto queryLast100GlobalContractTransactions(
			QueryContractTransactionsOutputDto queryContractTransactionsOutputDto) {
		RestTemplate restTemplate = new RestTemplate();
		Map m = new HashMap<>();
	    m.put("orgName", orgName);
	    m.put("userName", userName);
        HttpEntity<Object> postReq = new HttpEntity(m);
        ResponseEntity<Object> response = restTemplate.postForEntity(queryLast100GlobalContractTransactionsUrl, postReq, Object.class);
		ObjectMapper mapper = new ObjectMapper();
		String usersObject;
		try {
			usersObject = mapper .writeValueAsString(response.getBody());
			System.out.println(usersObject);
			queryContractTransactionsOutputDto = mapper.readValue(usersObject, new TypeReference<QueryContractTransactionsOutputDto>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryContractTransactionsOutputDto;
	}

	@Override
	public LockAvailableClusterOutputDto lockCluster(LockAvailableClusterInputDto lockAvailableClusterInputDto,
			LockAvailableClusterOutputDto lockAvailableClusterOutputDto) {
		if(null!=lockAvailableClusterInputDto&&null!=lockAvailableClusterInputDto.getScenario()&&lockAvailableClusterInputDto.getScenario().equalsIgnoreCase("scenario2")) {
			lockAvailableClusterOutputDto = storedProcessDao.lockPersonalCluster(lockAvailableClusterInputDto, lockAvailableClusterOutputDto);
		}
		else if(null!=lockAvailableClusterInputDto&&null!=lockAvailableClusterInputDto.getScenario()&&(lockAvailableClusterInputDto.getScenario().equalsIgnoreCase("normal")||lockAvailableClusterInputDto.getScenario().equalsIgnoreCase("scenario1"))) {
			storedProcessDao.lockAvailableClusterStoredProcess(lockAvailableClusterInputDto,lockAvailableClusterOutputDto);
		}
		return lockAvailableClusterOutputDto;
	}

	@Override
	public AddClusterOutputDto addCluster(AddClusterInputDto addClusterInputDto,
			AddClusterOutputDto addClusterOutputDto) {
		List<ClusterMasterModel> clusters = clusterMasterModelDao.findAll();
		//List<ClusterMasterModel> clusters = clusterMasterModelDao.findAllByForestId(addClusterInputDto.getForestId());
		for(ClusterMasterModel cluster : clusters) {
			ClusterDetails clusterDetails = clusterMgmtHelper.convertClusterMasterModelToClusterDetails(cluster);
			System.out.println("Cluster ID = "+clusterDetails.getCluster_id()+" pin point = "+clusterDetails.getCluster_coordinates());
			RestTemplate restTemplate = new RestTemplate();
			Map m = new HashMap<>();
			m.put("orgName", orgName);
		    m.put("userName", userName);
		    m.put("clusterDetails", clusterDetails);
	        HttpEntity<Object> postReq = new HttpEntity(m);
	        ResponseEntity<Object> response = restTemplate.postForEntity(addClusterUrl, postReq, Object.class);
			ObjectMapper mapper = new ObjectMapper();
			String usersObject;
			try {
				usersObject = mapper .writeValueAsString(response.getBody());
				System.out.println(usersObject);
				RestTemplateResponseDto responseDto = mapper.readValue(usersObject, new TypeReference<RestTemplateResponseDto>(){});
				addClusterOutputDto.setStatus(responseDto.getSuccess());
				addClusterOutputDto.setMessage(responseDto.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				addClusterOutputDto.setStatus("false");
				addClusterOutputDto.setMessage("Error while adding clusters");
				break;
			}
		}
		return addClusterOutputDto;
	}

	@Override
	public TakerGiftingScenarioOutputDto takerGiftingScenario(TakerGiftingScenarioInputDto takerGiftingScenarioInputDto,
			TakerGiftingScenarioOutputDto takerGiftingScenarioOutputDto) {
		TransactionScenarioWorkspaceDto workspace = new TransactionScenarioWorkspaceDto();
		String partiallyBrokenContractId = "";
		String newlyCreatedContractId = "";
		if(null!=takerGiftingScenarioInputDto&&null==takerGiftingScenarioInputDto.getContractPeriod()) {
			takerGiftingScenarioInputDto.setContractPeriod("10");
		}
		takerGiftingScenarioOutputDto = storedProcessDao.takerGiftingScenario(takerGiftingScenarioInputDto, takerGiftingScenarioOutputDto, workspace);
		String[] impactedContractIds = extractImpactedContracts(takerGiftingScenarioOutputDto.getImpactedContracts());
		partiallyBrokenContractId = impactedContractIds[0];
		newlyCreatedContractId = impactedContractIds[1];
		boolean addNewContractFlag = false;
		if(takerGiftingScenarioOutputDto.getGiftType().equalsIgnoreCase("scenario2")) {
			if(workspace.isDoPost()) {
				List<ContractMasterModel> contracts = contractMasterModelDao.findAllByReferralCode(takerGiftingScenarioInputDto.getReferralCode());
				for(ContractMasterModel contract : contracts) {
					if(contract.getContractId().equalsIgnoreCase(newlyCreatedContractId)) {
						addNewContractFlag = true;
					}
					else if(contract.getContractId().equalsIgnoreCase(partiallyBrokenContractId)){
						List<ClusterMasterModel> clusters = clusterMasterModelDao.findAllByContractId(newlyCreatedContractId);
						List<String> clusterIds = new ArrayList<>();
						for(ClusterMasterModel cluster : clusters) {
							clusterIds.add(cluster.getClusterId());
						}
						editContract(contract.getContractId(), takerGiftingScenarioInputDto.getGiftStatus(), takerGiftingScenarioOutputDto, clusterIds, "D");
					}
					else {
						List<String> clusterIds = new ArrayList<>();
						editContract(contract.getContractId(), takerGiftingScenarioInputDto.getGiftStatus(), takerGiftingScenarioOutputDto, clusterIds, "A");
					}
				}
				
				
				if(addNewContractFlag) {
					addContract(newlyCreatedContractId, takerGiftingScenarioInputDto.getGiftStatus(), takerGiftingScenarioOutputDto);
				}
			}
		}
		else if(takerGiftingScenarioOutputDto.getGiftType().equalsIgnoreCase("scenario1")) {
			List<ContractMasterModel> contracts = contractMasterModelDao.findAllByReferralCode(takerGiftingScenarioInputDto.getReferralCode());
			for(ContractMasterModel contract : contracts) {
				addContract(contract.getContractId(), takerGiftingScenarioInputDto.getGiftStatus(), takerGiftingScenarioOutputDto);
			}
		}
		return takerGiftingScenarioOutputDto;
	}

	

	private String[] extractImpactedContracts(String impactedContracts) {
		String s[] = new String[2];
		if(null==impactedContracts||impactedContracts.equalsIgnoreCase("NA")||impactedContracts.equalsIgnoreCase("na")||impactedContracts.equalsIgnoreCase("")) {
			s[0]="-1";
			s[1]="-1";
		}
		else {
			impactedContracts = impactedContracts.replace("G", "");
			impactedContracts = impactedContracts.replace("T", "");
			impactedContracts = impactedContracts.replace("=", "");
			impactedContracts = impactedContracts.replace("=", "");
			impactedContracts = impactedContracts.trim();
			s = impactedContracts.split("-");
		}
		return s;
	}

	public void addContract(String contractId, String giftStatus, TakerGiftingScenarioOutputDto takerGiftingScenarioOutputDto) {
		ContractMasterModel contract = contractMasterModelDao.findByContractId(contractId);
		List<ClusterMasterModel> clusters = clusterMasterModelDao.findAllByContractId(contractId);
		ContractDetails contractDetails = clusterMgmtHelper.convertContractMasterModelToContractDetailsForAdd(contract, clusters, giftStatus);
		RestTemplate restTemplate = new RestTemplate();
		Map m = new HashMap<>();
		m.put("orgName", orgName);
	    m.put("userName", userName);
	    m.put("contractDetails", contractDetails);
        HttpEntity<Object> postReq = new HttpEntity(m);
        ResponseEntity<Object> response = restTemplate.postForEntity(createContractUrl, postReq, Object.class);
		ObjectMapper mapper = new ObjectMapper();
		String usersObject;
		try {
			usersObject = mapper .writeValueAsString(response.getBody());
			System.out.println(usersObject);
			RestTemplateResponseDto responseDto = mapper.readValue(usersObject, new TypeReference<RestTemplateResponseDto>(){});
			takerGiftingScenarioOutputDto.setStatus(responseDto.getSuccess());
			takerGiftingScenarioOutputDto.setDescription(responseDto.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void editContract(String contractId, String giftStatus,
			TakerGiftingScenarioOutputDto takerGiftingScenarioOutputDto, List<String> clusterIds, String clusterAction) {
		ContractMasterModel contract = contractMasterModelDao.findByContractId(contractId);
		List<ClusterMasterModel> clusters = clusterMasterModelDao.findAllByContractId(contractId);
		ContractDetails contractDetails = clusterMgmtHelper.convertContractMasterModelToContractDetailsForEdit(contract, clusters, giftStatus, clusterIds, clusterAction);
		RestTemplate restTemplate = new RestTemplate();
		Map m = new HashMap<>();
		m.put("orgName", orgName);
	    m.put("userName", userName);
	    m.put("contractDetails", contractDetails);
        HttpEntity<Object> postReq = new HttpEntity(m);
        ResponseEntity<Object> response = restTemplate.postForEntity(editContractUrl, postReq, Object.class);
		ObjectMapper mapper = new ObjectMapper();
		String usersObject;
		try {
			usersObject = mapper .writeValueAsString(response.getBody());
			System.out.println(usersObject);
			RestTemplateResponseDto responseDto = mapper.readValue(usersObject, new TypeReference<RestTemplateResponseDto>(){});
			takerGiftingScenarioOutputDto.setStatus(responseDto.getSuccess());
			takerGiftingScenarioOutputDto.setDescription(responseDto.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public FilteredResultDto queryContractsByFilter(FilterQueryDto filterQueryDto, FilteredResultDto resultList) {
		List<ContractInfo> contractInfoList = new ArrayList<>();
		List<ContractMasterModel> contracts = contractMasterModelDao.findAllByUserId(filterQueryDto.getUserId());
		for(ContractMasterModel contract : contracts) {
			ContractInfo contractInfo = clusterMgmtHelper.convertContractMasterModelToContractInfo(contract);
			contractInfoList.add(contractInfo);
		}
		resultList.setContractInfoList(contractInfoList);
		return resultList;
	}
}
