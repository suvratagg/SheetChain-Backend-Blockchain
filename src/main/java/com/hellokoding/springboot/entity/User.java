package com.hellokoding.springboot.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author suvrat.aggarwal
 *
 */
@Entity(name = "user")
@Table(name = "user")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This field contains user id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;
	
	/**
	 * This field contains first name
	 */
	@Column(name = "first_name")
	private String firstName;
	
	/**
	 * This field contains last name
	 */
	@Column(name = "last_name")
	private String lastName;
	
	/**
	 * This field contains username
	 */
	@Column(name = "username")
	private String username;

	/**
	 * This field contains address
	 */
	@Column(name = "address")
	private String address;
	
	/**
	 * This field contains age
	 */
	@Column(name = "age")
	private int age;
	
	/**
	 * This field contains user type
	 */
	@Column(name = "user_type")
	private String userType;
	
	/**
	 * This field contains employee code
	 */
	@Column(name = "employee_code")
	private String employeeCode;

	/**
	 * This field contains employee status
	 */
	@Column(name = "employee_status")
	private String employeeStatus;

	/**
	 * This field contains employee report
	 */
	@Column(name = "employee_report")
	private String employeeReport;
	
	/**
	 * This field contains email id
	 */
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;
	
	/**
	 * Constructor without fields
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param userId
	 * @param firstName
	 * @param lastName
	 * @param username
	 * @param address
	 * @param age
	 * @param userType
	 * @param employeeCode
	 * @param employeeStatus
	 * @param employeeReport
	 * @param email
	 */
	public User(Long userId, String firstName, String lastName, String username, String address, int age,
			String userType, String employeeCode, String employeeStatus, String employeeReport, String email, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.address = address;
		this.age = age;
		this.userType = userType;
		this.employeeCode = employeeCode;
		this.employeeStatus = employeeStatus;
		this.employeeReport = employeeReport;
		this.email = email;
		this.password = password;
	}

	/**
	 * @return
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 */
	public void setAge(int age) {
		this.age = age;
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
	public String getEmployeeCode() {
		return employeeCode;
	}

	/**
	 * @param employeeCode
	 */
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	/**
	 * @return
	 */
	public String getEmployeeStatus() {
		return employeeStatus;
	}

	/**
	 * @param employeeStatus
	 */
	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	/**
	 * @return
	 */
	public String getEmployeeReport() {
		return employeeReport;
	}

	/**
	 * @param employeeReport
	 */
	public void setEmployeeReport(String employeeReport) {
		this.employeeReport = employeeReport;
	}
	
	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param userType
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
