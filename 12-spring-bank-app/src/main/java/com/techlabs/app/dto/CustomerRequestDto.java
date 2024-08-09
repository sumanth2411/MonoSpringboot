package com.techlabs.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequestDto {

	private long customer_id;
	
	@NotBlank(message="first name cannot be blank")
	@Size(min = 2 , max = 50)
	private String firstName;
	
	@NotBlank(message="last name cannot be blank")
	@Size(min = 2,max = 50)
	private String lastName;
	
	@NotNull
	private double totalBalance;
	
	private List<AccountRequestDto> accounts;

	public CustomerRequestDto(long customer_id,
			@NotBlank(message = "first name cannot be blank") @Size(min = 2, max = 50) String firstName,
			@NotBlank(message = "last name cannot be blank") @Size(min = 2, max = 50) String lastName,
			@NotNull double totalBalance, List<AccountRequestDto> accounts) {
		super();
		this.customer_id = customer_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.totalBalance = totalBalance;
		this.accounts = accounts;
	}

	public CustomerRequestDto() {
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

	public List<AccountRequestDto> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<AccountRequestDto> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "CustomerRequestDto [customer_id=" + customer_id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", totalBalance=" + totalBalance + ", accounts=" + accounts + "]";
	}
	
	
	
}
