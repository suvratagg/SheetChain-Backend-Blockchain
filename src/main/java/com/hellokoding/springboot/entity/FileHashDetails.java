package com.hellokoding.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author akashmalik
 *
 */
@Entity(name = "filehashdetails")
@Table(name = "filehashdetails")
@JsonPropertyOrder({"fileHashPK", "fileHash"})
public class FileHashDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3274084429975183917L;

//	@Column(name = "user_name")
//	private String username;
//	
//	@Column(name = "file_name")
//	private String fileName;
//	
	@EmbeddedId
	@JsonProperty("fileHashPK")
	private FileHashPK fileHashPK;
	
	@Column(name = "hash")
	@JsonProperty("fileHash")
	private String fileHash;

	
	public FileHashPK getFileHashPK() {
		return fileHashPK;
	}
	//@JsonSetter("fileHashPK")
	public void setFileHashPK(FileHashPK fileHashPK) {
		this.fileHashPK = fileHashPK;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}
}
