package com.techlabs.app.dto;

import lombok.Data;

@Data
public class LoginDto {

	private String email;
	private String password;
	private boolean admin;
	public LoginDto(String email, String password, boolean admin) {
		super();
		this.email = email;
		this.password = password;
		this.admin = admin;
	}
	public LoginDto() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", password=" + password + ", admin=" + admin + "]";
	}
	
	
}
