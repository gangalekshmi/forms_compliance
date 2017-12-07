package com.cognizant.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Document(collection = "loginInfo")
public class Login {

	public Login() {
	}

	public Login(long employeeId, String password) {
		this.employeeId = employeeId;
		this.password = password;
	}

	@Id
	private long employeeId;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Transient
	private String status;

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
