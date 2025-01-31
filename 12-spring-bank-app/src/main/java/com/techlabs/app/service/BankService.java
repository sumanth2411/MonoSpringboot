package com.techlabs.app.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.itextpdf.text.DocumentException;
import com.techlabs.app.dto.AccountResponseDto;
import com.techlabs.app.dto.CustomerRequestDto;
import com.techlabs.app.dto.CustomerResponseDto;
import com.techlabs.app.dto.ProfileRequestDto;
import com.techlabs.app.dto.ProfileResponseDto;
import com.techlabs.app.dto.TransactionResponseDto;
import com.techlabs.app.dto.UserResponseDto;
import com.techlabs.app.util.PagedResponse;

import jakarta.mail.MessagingException;

public interface BankService {

	PagedResponse<TransactionResponseDto> listAllTransactions(LocalDateTime fromDate, LocalDateTime toDate,
			int pageNumber, int pageSize, String sortBy, String sortDirection);

	PagedResponse<TransactionResponseDto> viewAllTransaction(LocalDateTime fromDate, LocalDateTime toDate, int page, int size, String sortBy, String direction);
	
	UserResponseDto registerCustomer(CustomerRequestDto customerRequestDto, long userId);

	CustomerResponseDto createCustomerAccount(long customerId, int bankId);

	List<CustomerResponseDto> retrieveAllCustomers();

	CustomerResponseDto findCustomerById(long customerId);

	TransactionResponseDto processTransaction(long senderAccountNumber, long receiverAccountNumber, double amount);

	PagedResponse<TransactionResponseDto> getAccountPassbook(long accountNumber, LocalDateTime fromDate, LocalDateTime toDate, int page, int size, String sortBy, String direction) throws DocumentException, IOException, MessagingException;

	String updateCustomerProfile(ProfileRequestDto profileRequestDto);
	
	AccountResponseDto depositAmount(long accountNumber, double amount);

	List<AccountResponseDto> getAccounts();

	String deleteCustomer(long customerID);

	String activateCustomer(long customerID);

	String deleteAccount(long accountNumber);

	String activateAccount(long accountNumber);

	AccountResponseDto viewBalance(long accountNumber);


	
}
