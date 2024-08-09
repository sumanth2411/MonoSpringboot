package com.techlabs.app.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_account")
	private Account senderAccount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "receiver_account")
	private Account receiverAccount;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	
	
	private LocalDateTime transactionDate;
	
	@NotNull
	private double amount;

	public Transaction(long id, Account senderAccount, Account receiverAccount, TransactionType transactionType,
			LocalDateTime transactionDate, @NotNull double amount) {
		super();
		this.id = id;
		this.senderAccount = senderAccount;
		this.receiverAccount = receiverAccount;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
	}

	public Transaction() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Account getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(Account receiverAccount) {
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
		return "Transaction [id=" + id + ", senderAccount=" + senderAccount + ", receiverAccount=" + receiverAccount
				+ ", transactionType=" + transactionType + ", transactionDate=" + transactionDate + ", amount=" + amount
				+ "]";
	}
	
	
}
