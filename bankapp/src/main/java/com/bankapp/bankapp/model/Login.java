package com.bankapp.bankapp.model;

public class Login {
	private Long username;
	private String password;
	private Long otp;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getUsername() {
		return username;
	}
	public void setUsername(Long username) {
		this.username = username;
	}
	public Long getUserID() {
		return username;
	}
	public void setUserID(Long username) {
		this.username = username;
	}
	public Long getOtp() {
		return otp;
	}
	public void setOtp(Long otp) {
		this.otp = otp;
	}
}
