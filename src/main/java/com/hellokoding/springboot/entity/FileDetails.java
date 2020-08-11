package com.hellokoding.springboot.entity;

import java.io.Serializable;

/**
 * 
 * @author akashmalik
 *
 */
public class FileDetails implements Serializable{

	private static final long serialVersionUID = 6724131747281065574L;

	private String fileName;
		
	private String fileHash;
	
	private String userName;
	
	private String version;
	
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public FileDetails() {
		
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
	
	
}
