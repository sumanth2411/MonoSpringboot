package com.techlabs.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class CustomerResponseDto {

	private long customer_id;
	
	private String firstName;
	
	
	private String lastName;
	
	
	private double totalBalance;
	
	private List<AccountResponseDto> accounts;

	public CustomerResponseDto(long customer_id, String firstName, String lastName, double totalBalance,
			List<AccountResponseDto> accounts) {
		super();
		this.customer_id = customer_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalBalance = totalBalance;
		this.accounts = accounts;
	}

	public CustomerResponseDto() {
		super();
	}

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public List<AccountResponseDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountResponseDto> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "CustomerResponseDto [customer_id=" + customer_id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", totalBalance=" + totalBalance + ", accounts=" + accounts + "]";
	}
	
	
}
