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

import com.techlabs.app.dto.ContactRequestDto;
import com.techlabs.app.dto.ContactResponseDto;
import com.techlabs.app.service.ContactService;
import com.techlabs.app.Util.PagedResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/contacts")
public class ContactController {

	private ContactService contactService;
	
	
	
	public ContactController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}

	@PostMapping
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "add contact to user")
	public ResponseEntity<ContactResponseDto> addContact(@Valid @RequestBody ContactRequestDto contactRequestDto){
		return new ResponseEntity<ContactResponseDto>(contactService.addContact(contactRequestDto),HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "get all contacts")
	public ResponseEntity<PagedResponse<ContactResponseDto>> getAllContacts(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "contactId") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction){
		 return  new ResponseEntity<PagedResponse<ContactResponseDto>>(contactService.getAllContacts(page, size, sortBy, direction),HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "get contact by id")
	public ResponseEntity<ContactResponseDto> getContactId(@PathVariable(name="id")long id){
		return new ResponseEntity<ContactResponseDto>(contactService.findContactById(id),HttpStatus.OK);
		
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "update contact by id")
	public ResponseEntity<ContactResponseDto> updateContactbyId(@PathVariable(name="id")long id,@RequestBody ContactRequestDto contactRequestDto){
		return new ResponseEntity<ContactResponseDto>(contactService.updateContactDetails(id,contactRequestDto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('STAFF')")
	@Operation(summary = "delete contact by id")
	public ResponseEntity<String> deleteContactId(@PathVariable(name="id")long id){
		return new ResponseEntity<String>(contactService.deleteContactById(id),HttpStatus.OK);
		
	}
	
}
