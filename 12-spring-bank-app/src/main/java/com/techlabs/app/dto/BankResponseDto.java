package com.techlabs.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class BankResponseDto {

	private int bank_id;
	
	private String fullName;
	
	private String abbreviation;
	
	
	private List<AccountResponseDto> accounts;


	public BankResponseDto(int bank_id, String fullName, String abbreviation, List<AccountResponseDto> accounts) {
		super();
		this.bank_id = bank_id;
		this.fullName = fullName;
		this.abbreviation = abbreviation;
		this.accounts = accounts;
	}


	public BankResponseDto() {
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


	public List<AccountResponseDto> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<AccountResponseDto> accounts) {
		this.accounts = accounts;
	}


	@Override
	public String toString() {
		return "BankResponseDto [bank_id=" + bank_id + ", fullName=" + fullName + ", abbreviation=" + abbreviation
				+ ", accounts=" + accounts + "]";
	}
	
	
	
}
