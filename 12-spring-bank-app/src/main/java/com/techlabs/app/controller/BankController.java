package com.techlabs.app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.techlabs.app.dto.CustomerRequestDto;
import com.techlabs.app.dto.CustomerResponseDto;
import com.techlabs.app.dto.ProfileRequestDto;
import com.techlabs.app.dto.ProfileResponseDto;
import com.techlabs.app.dto.TransactionResponseDto;
import com.techlabs.app.dto.UserResponseDto;
import com.techlabs.app.service.BankService;
import com.techlabs.app.util.PagedResponse;

import io.jsonwebtoken.io.IOException;
import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/bank")
@EnableMethodSecurity
public class BankController {

    private BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }
	
    @GetMapping("/admin/transactions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PagedResponse<TransactionResponseDto>> listAllTransactions(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "from", defaultValue = "#{T(java.time.LocalDateTime).now().minusDays(30).toString()}") String from,
            @RequestParam(name = "to", defaultValue = "#{T(java.time.LocalDateTime).now().toString()}") String to) {

        LocalDateTime fromDate = LocalDateTime.parse(from);
        LocalDateTime toDate = LocalDateTime.parse(to);

        return new ResponseEntity<>(bankService.listAllTransactions(fromDate, toDate, page, size, sortBy, direction), HttpStatus.OK);
    }

    @PostMapping("/admin/customers/{userID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponseDto> registerCustomer(@RequestBody CustomerRequestDto customerRequestDto,
                                                            @PathVariable(name = "userID") long userID) {
        return new ResponseEntity<>(bankService.registerCustomer(customerRequestDto, userID), HttpStatus.CREATED);
    }

    @PostMapping("/admin/banks/{bankId}/customers/{customerId}/accounts")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerResponseDto> createCustomerAccount(@PathVariable(name = "customerId") long customerId,
                                                                     @PathVariable(name = "bankId") int bankId) {
        return new ResponseEntity<>(bankService.createCustomerAccount(customerId, bankId), HttpStatus.CREATED);
    }

    @GetMapping("/admin/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CustomerResponseDto>> retrieveAllCustomers() {
        return new ResponseEntity<>(bankService.retrieveAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/admin/customers/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerResponseDto> findCustomerById(@PathVariable(name = "customerId") long customerId) {
        return new ResponseEntity<>(bankService.findCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping("/customers/transactions")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<TransactionResponseDto> processTransaction(
            @RequestParam(name = "senderAccountNumber") long senderAccountNumber,
            @RequestParam(name = "receiverAccountNumber") long receiverAccountNumber,
            @RequestParam(name = "amount") double amount) {
        return new ResponseEntity<>(bankService.processTransaction(senderAccountNumber, receiverAccountNumber, amount), HttpStatus.OK);
    }

	@GetMapping("/customers/passbook/{accountNumber}")
	@PreAuthorize("hasRole('USER')")
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
	public ResponseEntity<String> modifyProfile(@RequestBody ProfileRequestDto profileRequestDto) {
		return new ResponseEntity<String>(bankService.updateCustomerProfile(profileRequestDto),HttpStatus.OK);
	}

    @PutMapping("/customers/{accountNumber}/deposit")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AccountResponseDto> deposit(@PathVariable(name = "accountNumber") long accountNumber,
			@RequestParam(name = "amount") double amount) {
		return new ResponseEntity<AccountResponseDto>(bankService.depositAmount(accountNumber, amount),HttpStatus.OK);
	}
	@GetMapping("/customers/accounts")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<AccountResponseDto>> getAllAccounts() {
		return new ResponseEntity<List<AccountResponseDto>>(bankService.getAccounts(),HttpStatus.OK);
	}
	@DeleteMapping("admin/customers/{customerID}/deactivate")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCustomer(@PathVariable(name = "customerID")long customerID) {
		return new ResponseEntity<String>(bankService.deleteCustomer(customerID),HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("admin/customers/{customerID}/activate")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> activateExistingCustomer(@PathVariable(name = "customerID")long customerID) {
		return new ResponseEntity<String>(bankService.activateCustomer(customerID),HttpStatus.OK);
	}
	@DeleteMapping("admin/customers/accounts/{accountNumber}/deactivate")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteAccount(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<String>(bankService.deleteAccount(accountNumber),HttpStatus.NO_CONTENT);
	}
	@PutMapping("admin/customers/accounts/{accountNumber}/activate")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> activateExistingAccount(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<String>(bankService.activateAccount(accountNumber),HttpStatus.OK);
	}
	
	@GetMapping("customers/accounts/{accountNumber}/view-balance")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AccountResponseDto> viewBalance(@PathVariable(name = "accountNumber")long accountNumber) {
		return new ResponseEntity<AccountResponseDto>(bankService.viewBalance(accountNumber),HttpStatus.OK);
	}
	
    
}
