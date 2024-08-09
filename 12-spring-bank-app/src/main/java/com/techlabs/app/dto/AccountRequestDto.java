package com.techlabs.app.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AccountRequestDto {

	private long accountNumber;

	
	private BankRequestDto bankrequestDto;

	
	private CustomerRequestDto customerRequestDto;

	private List<TransactionRequestDto> sentTransactions;
	
	private List<TransactionRequestDto> receiverTransactions;

	@NotNull
	private double balance;

	public AccountRequestDto(long accountNumber, BankRequestDto bankrequestDto, CustomerRequestDto customerRequestDto,
			List<TransactionRequestDto> sentTransactions, List<TransactionRequestDto> receiverTransactions,
			@NotNull double balance) {
		super();
		this.accountNumber = accountNumber;
		this.bankrequestDto = bankrequestDto;
		this.customerRequestDto = customerRequestDto;
		this.sentTransactions = sentTransactions;
		this.receiverTransactions = receiverTransactions;
		this.balance = balance;
	}

	public AccountRequestDto() {
		super();
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BankRequestDto getBankrequestDto() {
		return bankrequestDto;
	}

	public void setBankrequestDto(BankRequestDto bankrequestDto) {
		this.bankrequestDto = bankrequestDto;
	}

	public CustomerRequestDto getCustomerRequestDto() {
		return customerRequestDto;
	}

	public void setCustomerRequestDto(CustomerRequestDto customerRequestDto) {
		this.customerRequestDto = customerRequestDto;
	}

	public List<TransactionRequestDto> getSentTransactions() {
		return sentTransactions;
	}

	public void setSentTransactions(List<TransactionRequestDto> sentTransactions) {
		this.sentTransactions = sentTransactions;
	}

	public List<TransactionRequestDto> getReceiverTransactions() {
		return receiverTransactions;
	}

	public void setReceiverTransactions(List<TransactionRequestDto> receiverTransactions) {
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
		return "AccountRequestDto [accountNumber=" + accountNumber + ", bankrequestDto=" + bankrequestDto
				+ ", customerRequestDto=" + customerRequestDto + ", sentTransactions=" + sentTransactions
				+ ", receiverTransactions=" + receiverTransactions + ", balance=" + balance + "]";
	}
	
	
}
