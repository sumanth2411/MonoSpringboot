package com.techlabs.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.techlabs.app.entity.Bank;
import com.techlabs.app.entity.Customer;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class AccountResponseDto {

	private long accountNumber;

	
	private Bank bank;

	
	private Customer customer;

	private List<TransactionResponseDto> sentTransactions;
	
	private List<TransactionResponseDto> receiverTransactions;

	private double balance;

	public AccountResponseDto(long accountNumber, Bank bank, Customer customer,
			List<TransactionResponseDto> sentTransactions, List<TransactionResponseDto> receiverTransactions,
			double balance) {
		super();
		this.accountNumber = accountNumber;
		this.bank = bank;
		this.customer = customer;
		this.sentTransactions = sentTransactions;
		this.receiverTransactions = receiverTransactions;
		this.balance = balance;
	}

	public AccountResponseDto() {
		super();
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<TransactionResponseDto> getSentTransactions() {
		return sentTransactions;
	}

	public void setSentTransactions(List<TransactionResponseDto> sentTransactions) {
		this.sentTransactions = sentTransactions;
	}

	public List<TransactionResponseDto> getReceiverTransactions() {
		return receiverTransactions;
	}

	public void setReceiverTransactions(List<TransactionResponseDto> receiverTransactions) {
		this.receiverTransactions = receiverTransactions;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "AccountResponseDto [accountNumber=" + accountNumber + ", bank=" + bank + ", customer=" + customer
				+ ", sentTransactions=" + sentTransactions + ", receiverTransactions=" + receiverTransactions
				+ ", balance=" + balance + "]";
	}
	
	
	
}
