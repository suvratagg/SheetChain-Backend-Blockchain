package com.hellokoding.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Embeddable
@JsonPropertyOrder({"username","fileName"})
public class FileHashPK implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6426862034926139176L;

	@Column(name = "user_name")
	@JsonProperty("username")
	private String username;
	
	@Column(name = "file_name")
	@JsonProperty("fileName")
	private String fileName;
	
}
