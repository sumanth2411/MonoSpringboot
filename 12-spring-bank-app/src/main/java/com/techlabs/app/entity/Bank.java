package com.techlabs.app.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Bank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bank_id;
	
	@NotBlank(message = "Bank name cannot be empty")
	private String fullName;
	
	@NotBlank
	private String abbreviation;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "bank")
	private List<Account> accounts;


	public Bank(int bank_id, @NotBlank(message = "Bank name cannot be empty") String fullName,
			@NotBlank String abbreviation, List<Account> accounts) {
		super();
		this.bank_id = bank_id;
		this.fullName = fullName;
		this.abbreviation = abbreviation;
		this.accounts = accounts;
	}


	public Bank() {
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


	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}


	@Override
	public String toString() {
		return "Bank [bank_id=" + bank_id + ", fullName=" + fullName + ", abbreviation=" + abbreviation + ", accounts="
				+ accounts + "]";
	}
	
	
}
