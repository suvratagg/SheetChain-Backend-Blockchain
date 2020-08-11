package com.hellokoding.springboot.entity;

import java.io.Serializable;

/**
 * 
 * @author akashmalik
 *
 */
public class WorkItemTxnDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4300489685646700856L;

	private String TransactionId;
	private java.time.Instant timestamp;
	private FileDetails fileDetails;
	private String timestampDate;
	
	
	
	public String getTimestampDate() {
		return timestampDate;
	}
	public void setTimestampDate(String timestampDate) {
		this.timestampDate = timestampDate;
	}
	public String getTransactionId() {
		return TransactionId;
	}
	public void setTransactionId(String transactionId) {
		TransactionId = transactionId;
	}
	public java.time.Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(java.time.Instant timestamp) {
		this.timestamp = timestamp;
	}
	public FileDetails getFileDetails() {
		return fileDetails;
	}
	public void setFileDetails(FileDetails fileDetails) {
		this.fileDetails = fileDetails;
	}
}
