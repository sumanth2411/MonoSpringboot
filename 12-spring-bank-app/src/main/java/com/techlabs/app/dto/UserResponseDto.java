package com.techlabs.app.dto;

import java.util.Set;

import com.techlabs.app.entity.Role;

import lombok.Data;
@Data
public class UserResponseDto {

	private Long id;

	private String email;
	private String password;
	private Set<Role> roles;
	private CustomerResponseDto customerResponseDto;
	public UserResponseDto(Long id, String email, String password, Set<Role> roles,
			CustomerResponseDto customerResponseDto) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.customerResponseDto = customerResponseDto;
	}
	public UserResponseDto() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public CustomerResponseDto getCustomerResponseDto() {
		return customerResponseDto;
	}
	public void setCustomerResponseDto(CustomerResponseDto customerResponseDto) {
		this.customerResponseDto = customerResponseDto;
	}
	@Override
	public String toString() {
		return "UserResponseDto [id=" + id + ", email=" + email + ", password=" + password + ", roles=" + roles
				+ ", customerResponseDto=" + customerResponseDto + "]";
	}
	
	
}
