package com.techlabs.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.app.dto.ContactDetailRequestDto;
import com.techlabs.app.dto.ContactDetailResponseDto;
import com.techlabs.app.service.ContactService;
import com.techlabs.app.Util.PagedResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contacts")
public class ContactDetailsController {

	private ContactService contactService;
	
	
	public ContactDetailsController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}


	@PostMapping("/{contactId}/details")
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "adding contact details")
	public ResponseEntity<ContactDetailResponseDto> createContactDetailsforContact(@Valid @RequestBody ContactDetailRequestDto contactDetailsRequestDto,@PathVariable(name="contactId") long contactId) {
		ContactDetailResponseDto contact = contactService.createContactDetailsforContact(contactDetailsRequestDto,contactId);

		return new ResponseEntity<ContactDetailResponseDto>(contact, HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/{contactId}/details")
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "update contact details")
	public ResponseEntity<ContactDetailResponseDto> updateContactDetailsforContact(@RequestBody ContactDetailRequestDto contactDetailsRequestDto,@PathVariable(name="contactId") long contactId,@RequestParam(name="contactDetailsId") long contactDetailsId){
		return new ResponseEntity<ContactDetailResponseDto>(contactService.updateContactDetails(contactId, contactDetailsRequestDto,contactDetailsId),HttpStatus.OK);
	}
	
	@GetMapping("/{contactId}/details")
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "get all contact details")
	public ResponseEntity<PagedResponse<ContactDetailResponseDto>> getAllContactDetails(@RequestParam(name = "page", defaultValue = "0") int page,
	@RequestParam(name = "size", defaultValue = "5") int size,
	@RequestParam(name = "sortBy", defaultValue = "contactDetailsId") String sortBy,
	@RequestParam(name = "direction", defaultValue = "asc") String direction,
	@PathVariable(name="contactId")long contactId){
		
		return new ResponseEntity<PagedResponse<ContactDetailResponseDto>>(contactService.getAllContactDetails(page,size,sortBy,direction,contactId),HttpStatus.OK);
	}
	
	@GetMapping("/{contactId}/details/{contactdetails_id}")
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "get contactDetails by id")
	public ResponseEntity<ContactDetailResponseDto> getContactDetailsById(@PathVariable(name="contactId")long contactId,@PathVariable(name="contactdetails_id")long contactdetails_id){
		return new ResponseEntity<ContactDetailResponseDto>(contactService.getContactDetailsById(contactId,contactdetails_id),HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/{contactId}/details/{contactdetails_id}")
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "delete contact details by id")
	public ResponseEntity<String> deleteCustomerDetailsById(@PathVariable(name="contactId")long contactId,@PathVariable(name="contactdetails_id")long contactdetails_id){
		return new ResponseEntity<String>(contactService.deleteContacDetailstById(contactdetails_id,contactId),HttpStatus.OK);
	}
	
}
