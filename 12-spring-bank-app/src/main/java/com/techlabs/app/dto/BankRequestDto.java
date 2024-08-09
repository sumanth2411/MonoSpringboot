package com.techlabs.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BankRequestDto {

	private int bank_id;
	
	@NotBlank(message = "Bank name cannot be empty")
	private String fullName;
	
	@NotBlank
	private String abbreviation;
	
	
	private List<AccountRequestDto> accounts;


	public BankRequestDto(int bank_id, @NotBlank(message = "Bank name cannot be empty") String fullName,
			@NotBlank String abbreviation, List<AccountRequestDto> accounts) {
		super();
		this.bank_id = bank_id;
		this.fullName = fullName;
		this.abbreviation = abbreviation;
		this.accounts = accounts;
	}


	public BankRequestDto() {
		super();
	}


	public int getBank_id() {
		return bank_id;
	}


	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getAbbreviation() {
		return abbreviation;
	}


	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}


	public List<AccountRequestDto> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<AccountRequestDto> accounts) {
		this.accounts = accounts;
	}


	@Override
	public String toString() {
		return "BankRequestDto [bank_id=" + bank_id + ", fullName=" + fullName + ", abbreviation=" + abbreviation
				+ ", accounts=" + accounts + "]";
	}
	
	
}
