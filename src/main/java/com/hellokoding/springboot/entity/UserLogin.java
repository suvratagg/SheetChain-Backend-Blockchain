package com.hellokoding.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author suvrat.aggarwal
 *
 */
@Entity(name = "login")
@Table(name = "login")
public class UserLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field contains username
	 */
	@Id
	@Column(name = "username")
	private String username;

	/**
	 * This field contains password
	 */
	@Column(name = "password")
	private String password;

	/**
	 * This field contains first name
	 */
	@Column(name = "firstname")
	private String firstName;

	/**
	 * This field contains last name
	 */
	@Column(name = "lastname")
	private String lastName;

	/**
	 * This field contains user type
	 */
	@Column(name = "usertype")
	private String userType;
	
	/**
	 * This field contains email id
	 */
	@Column(name = "email")
	private String email;

	/**
	 * Constructor without fields
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param userType
	 * @param email
	 */
	public UserLogin(String username, String password, String firstName, String lastName, String userType,String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.email = email;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param userType
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
