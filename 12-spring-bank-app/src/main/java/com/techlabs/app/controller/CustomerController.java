package com.techlabs.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.techlabs.app.dto.AccountResponseDto;
import com.techlabs.app.dto.ProfileRequestDto;
import com.techlabs.app.dto.TransactionResponseDto;
import com.techlabs.app.service.BankService;
import com.techlabs.app.util.PagedResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/bank")
@EnableMethodSecurity
public class CustomerController {

    private BankService bankService;

    public CustomerController(BankService bankService) {
        this.bankService = bankService;
    }
    
    @PostMapping("/customers/transactions")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Process Transaction")
    public ResponseEntity<TransactionResponseDto> processTransaction(
            @RequestParam(name = "senderAccountNumber") long senderAccountNumber,
            @RequestParam(name = "receiverAccountNumber") long receiverAccountNumber,
            @RequestParam(name = "amount") double amount) {
        return new ResponseEntity<>(bankService.processTransaction(senderAccountNumber, receiverAccountNumber, amount), HttpStatus.OK);
    }

	@GetMapping("/customers/passbook/{accountNumber}")
	@PreAuthorize("hasRole('USER')")
	 @Operation(summary = "Passbook")
	public ResponseEntity<PagedResponse<TransactionResponseDto>> getAccountPassbook(@PathVariable(name = "accountNumber") long accountNumber,
			@RequestParam(name = "from", defaultValue = "#{T(java.time.LocalDateTime).now().minusDays(30).toString()}") String from,
			@RequestParam(name = "to", defaultValue = "#{T(java.time.LocalDateTime).now().toString()}") String to,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction) throws DocumentException, MessagingException, java.io.IOException {
		LocalDateTime fromDate = LocalDateTime.parse(from);
		LocalDateTime toDate = LocalDateTime.parse(to);
		return new ResponseEntity<PagedResponse<TransactionResponseDto>>(bankService.getAccountPassbook(accountNumber, fromDate, toDate, page, size, sortBy, direction),HttpStatus.OK);
	}

//    @PutMapping("/customers/profile")
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public ProfileResponseDto modifyProfile(@RequestBody ProfileRequestDto profileRequestDto) {
//        return bankService.updateCustomerProfile(profileRequestDto, extractUsernameFromSecurityContext());
//    }
    
	@PutMapping("/customers/profile")
	@PreAuthorize("hasRole('USER')")
	 @Operation(summary = "Update Profile")
	public ResponseEntity<String> modifyProfile(@RequestBody ProfileRequestDto profileRequestDto) {
		return new ResponseEntity<String>(bankService.updateCustomerProfile(profileRequestDto),HttpStatus.OK);
	}

    @PutMapping("/customers/{accountNumber}/deposit")
	@PreAuthorize("hasRole('USER')")
    @Operation(summary = "Deposit")
	public ResponseEntity<AccountResponseDto> deposit(@PathVariable(name = "accountNumber") long accountNumber,
			@RequestParam(name = "amount") double amount) {
		return new ResponseEntity<AccountResponseDto>(bankService.depositAmount(accountNumber, amount),HttpStatus.OK);
	}
	@GetMapping("/customers/accounts")
	@PreAuthorize("hasRole('USER')")
	 @Operation(summary = "Get all accounts")
	public ResponseEntity<List<AccountResponseDto>> getAllAccounts() {
		return new ResponseEntity<List<AccountResponseDto>>(bankService.getAccounts(),HttpStatus.OK);
	}
	
	@GetMapping("customers/accounts/{accountNumber}/view-balance")
	@PreAuthorize("hasRole('USER')")
	 @Operation(summary = "View Balance")
	public ResponseEntity<AccountResponseDto> viewBalance(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<AccountResponseDto>(bankService.viewBalance(accountNumber),HttpStatus.OK);
	}
}
