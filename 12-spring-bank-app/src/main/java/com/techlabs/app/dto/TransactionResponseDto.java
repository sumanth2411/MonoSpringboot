package com.techlabs.app.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.techlabs.app.entity.TransactionType;

import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class TransactionResponseDto {

	private long id;
	
	
	private AccountResponseDto senderAccount;
	 
	
	
	private AccountResponseDto receiverAccount;
	

	private TransactionType transactionType;
	
	
	private LocalDateTime transactionDate;

	private double amount;

	public TransactionResponseDto(long id, AccountResponseDto senderAccount, AccountResponseDto receiverAccount,
			TransactionType transactionType, LocalDateTime transactionDate, double amount) {
		super();
		this.id = id;
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
	}

	public TransactionResponseDto() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AccountResponseDto getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(AccountResponseDto senderAccount) {
		this.senderAccount = senderAccount;
	}

	public AccountResponseDto getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(AccountResponseDto receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDateTime transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "TransactionResponseDto [id=" + id + ", senderAccount=" + senderAccount + ", receiverAccount="
				+ receiverAccount + ", transactionType=" + transactionType + ", transactionDate=" + transactionDate
				+ ", amount=" + amount + "]";
	}
	
	
}
