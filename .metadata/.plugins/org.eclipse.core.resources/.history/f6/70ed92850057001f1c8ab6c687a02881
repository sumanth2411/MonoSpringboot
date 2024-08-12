package com.techlabs.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterDto {

	private String name;
	private String email;
	private String password;
	private boolean admin;
	
	public RegisterDto(String name, String email, String password, boolean admin) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.admin = admin;
	}
	
	public RegisterDto() {
		super();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "RegisterDto [name=" + name + ", email=" + email + ", password=" + password
				+ ", admin=" + admin + "]";
	}
	
	
}
