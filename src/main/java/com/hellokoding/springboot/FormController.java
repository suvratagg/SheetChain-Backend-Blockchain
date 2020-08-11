package com.hellokoding.springboot;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.hyperledger.fabric.sdk.ChaincodeID;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.Orderer;
import org.hyperledger.fabric.sdk.Peer;
import org.hyperledger.fabric.sdk.ProposalResponse;
import org.hyperledger.fabric.sdk.QueryByChaincodeRequest;
import org.hyperledger.fabric.sdk.TransactionProposalRequest;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.ProposalException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.SerializationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.hellokoding.springboot.entity.AppUser;
import com.hellokoding.springboot.entity.FileDetails;
import com.hellokoding.springboot.entity.WorkItemTxnDetails;
import com.hellokoding.springboot.service.UserService;

/**
 * 
 * @author akashmalik
 *
 */
@Controller
public class FormController {

	private static final String CREATOR = "peer1";
	private static final String CHANNEL = "mychannel";
	private static final String CHANNEL_NAME = "sheetchainchannel";
	private static final String UPDATE = "update";
	private static final String QUERYALL = "queryAll";
	private static final String QUERY = "query";
	private static final String TXN_HISTORY = "txnHistory";

	private static DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
	 private  final Logger logger = LoggerFactory.getLogger(this.getClass());
	 
	 private HFClient client = HFClient.createNewInstance();
	 
	 @Autowired
	 private UserService userService;
	 
	
	  public FormController() {
	  
	  try { logger.info("in constructor"); startChaincode(); } 
	  catch (Exception e)
	  { // TODO Auto-generated
		  e.printStackTrace(); } }
	  
	 

	
	@SuppressWarnings("unchecked")
	@GetMapping("/fetchFileDetails")
	@CrossOrigin(origins="*")
	@ResponseBody
	public Iterable<FileDetails> getQueryResult() throws Exception {
		List<FileDetails> response = new ArrayList<>();
		FileDetails fileDetails = new FileDetails();
		String queryDetails = "{\"selector\": {}}";
		try {
			
			logger.info("queryxxxxx::" + queryDetails);
			for (ProposalResponse pr : getChannelFoRQuery(QUERYALL, queryDetails)) {
				logger.info("pr.getMessage()");
				logger.info(pr.getMessage());
				response = (List<FileDetails>) byteToObjConversion(pr.getChaincodeActionResponsePayload());
				logger.info("stringResponse lenght");
				logger.info("size"+ response.size());
				//logger.info("rev"+fileDetails.get_rev());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}

	@PostMapping("/insertFileDetails")
	@CrossOrigin(origins="*")
	@ResponseBody
	public String insertQueryReceived(@RequestBody FileDetails fileDetails) throws Exception {
		String hash = userService.fetchHashDetails(fileDetails.getUserName(), fileDetails.getFileHash());
		if(!Strings.isNullOrEmpty(hash)) {
			FileDetails fileDetail = viewWorkItem(fileDetails.getFileName());
			if(!fileDetail.getFileHash().equals(hash)) {
				return "This is not a latest version file. Please download latest version and then update";
			}
		}
		Channel channel = client.getChannel(CHANNEL);
		TransactionProposalRequest tpr = getChannelForTxn(UPDATE, SerializationUtils.serialize(fileDetails));

		Collection<ProposalResponse> res = channel.sendTransactionProposal(tpr);
		for (ProposalResponse pres : res) {
			logger.info(pres.getStatus().name());
			logger.info(pres.getMessage());
		}
		channel.sendTransaction(res);

		return "Record Successfully Inserted";
	}

	@PostMapping("/updateFileDetails")
	@CrossOrigin(origins="*")
	@ResponseBody
	public String updateWorkItem(@RequestBody FileDetails fileDetails) throws Exception {
		String hash = userService.fetchHashDetails(fileDetails.getUserName(), fileDetails.getFileName());
		if(!Strings.isNullOrEmpty(hash)) {
			FileDetails fileDetail = viewWorkItem(fileDetails.getFileName());
			if(!fileDetail.getFileHash().equals(hash)) {
				return "This is not a latest version file. Please download latest version and then update";
			}
		}
		Channel channel = client.getChannel(CHANNEL);
		TransactionProposalRequest tpr = getChannelForTxn(UPDATE, SerializationUtils.serialize(fileDetails));

		Collection<ProposalResponse> res = channel.sendTransactionProposal(tpr);
		for (ProposalResponse pres : res) {
			logger.info(pres.getStatus().name());
			logger.info(pres.getMessage());
		}
		channel.sendTransaction(res);

		return "Record Successfully Updated";
	}

	@GetMapping("/viewfileDetails")
	@CrossOrigin(origins="*")
	@ResponseBody
	public FileDetails viewWorkItem(@RequestParam String id) throws Exception {

		List<FileDetails> response = new ArrayList<>();
		for (ProposalResponse pr : getChannelFoRQuery(QUERY, id)) {
			logger.info(pr.getMessage());

			response = (List<FileDetails>) byteToObjConversion(pr.getChaincodeActionResponsePayload());
		}
		return response.get(0);

	}

	@SuppressWarnings("unchecked")
	@GetMapping("/getTxnHistory")
	@CrossOrigin(origins="*")
	@ResponseBody
	public Iterable<WorkItemTxnDetails> getTxnHistory(@RequestParam String id) throws Exception {

		List<WorkItemTxnDetails> response = new ArrayList<>();
		for (ProposalResponse pr : getChannelFoRQuery(TXN_HISTORY, id)) {
			logger.info(pr.getMessage());

			response = (List<WorkItemTxnDetails>) byteToObjConversion(pr.getChaincodeActionResponsePayload());
		}
		for(WorkItemTxnDetails workItemDetail: response) {
		LocalDateTime dateTime = LocalDateTime.ofEpochSecond(workItemDetail.getTimestamp().getEpochSecond(), 0, ZoneOffset.UTC);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm a", Locale.ENGLISH);
		String formattedDate = dateTime.format(formatter);
		System.out.println("date"+formattedDate);
		workItemDetail.setTimestampDate(formattedDate);
		}
		logger.info("response size::" + response.size());
		return response;

	}

	@GetMapping("/getBlockInfo")
	@CrossOrigin(origins="*")
	@ResponseBody
	public String getBlockInfo(@RequestParam String txnId) throws InvalidArgumentException, ProposalException {
		org.hyperledger.fabric.sdk.BlockchainInfo info = client.getChannel(CHANNEL).queryBlockchainInfo();
		System.out.println(info.getBlockchainInfo().getCurrentBlockHash());
		Long block = client.getChannel(CHANNEL).queryBlockByTransactionID(txnId).getBlockNumber();
		logger.info(Long.toString(block));
		return Long.toString(block);

	}

	
	


	private Collection<ProposalResponse> getChannelFoRQuery(String func, String input) {

		Channel channel = client.getChannel(CHANNEL);
		ChaincodeID fabcarCCId = ChaincodeID.newBuilder().setName(CHANNEL_NAME).build();
		QueryByChaincodeRequest qpr = client.newQueryProposalRequest();
		qpr.setChaincodeID(fabcarCCId);
		qpr.setFcn(func);
		qpr.setArgs(input);
		Collection<ProposalResponse> pr = null;
		try {
			pr = channel.queryByChaincode(qpr);
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProposalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pr;

	}

	private TransactionProposalRequest getChannelForTxn(String func, byte[] input) {

		TransactionProposalRequest tpr = client.newTransactionProposalRequest();
		ChaincodeID fabcarCCId = ChaincodeID.newBuilder().setName(CHANNEL_NAME).build();
		tpr.setChaincodeID(fabcarCCId);
		tpr.setFcn(func);
		tpr.setArgs(input);

		return tpr;

	}
	
	 
	
	public void startChaincode() throws Exception {

		logger.info("channel peer starting. ....");
		CryptoSuite cryptoSuite1 = CryptoSuite.Factory.getCryptoSuite();
		// String val = "http://localhost:8054"; 
		String val = "http://localhost:7054";
		HFCAClient caClient = HFCAClient.createNewInstance(val, null);
		caClient.setCryptoSuite(cryptoSuite1);
		String time_milli = "" + System.currentTimeMillis();
		Enrollment adminEnrollment = caClient.enroll("admin", "adminpw");
		AppUser admin = new AppUser("admin", "org1", "Org1MSP", adminEnrollment);
		RegistrationRequest rr = new RegistrationRequest(time_milli, "org1");
		String enrollmentSecret = caClient.register(rr, admin);
		Enrollment enrollment = caClient.enroll(time_milli, enrollmentSecret);

		AppUser appUser = new AppUser(time_milli, "org1", "Org1MSP", enrollment);
		CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
		client.setCryptoSuite(cryptoSuite);
		client.setUserContext(appUser);
		Peer peer = client.newPeer("peer0.org2.example.com", "grpc://localhost:9051");
		// Peer peer = client.newPeer("peer0", "grpc://localhost:8051");
		Orderer orderer = client.newOrderer("orderer.example.com", "grpc://localhost:7050");
		Channel channel1 = client.newChannel(CHANNEL);
		Peer peer1 = client.newPeer("peer0.org1.example.com", "grpc://localhost:7051");
		channel1.addPeer(peer);
		channel1.addPeer(peer1);
		channel1.addOrderer(orderer);
		channel1.initialize();
		logger.info("Network up successfully");
		// return "Chaincode Invoked Succesfully";
	}
	
	private List<? extends Object> byteToObjConversion(byte[] byteArray) {
		ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
		List<? extends Object> responseObj = new ArrayList<Object>();

		ObjectInput in = null;
		try {
			in = new ObjectInputStream(bis);
			responseObj = (List<? extends Object>) in.readObject();
		} catch (IOException ex) {
			// ignore close exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				// ignore close exception
			}
		}
		return responseObj;

	}
}


