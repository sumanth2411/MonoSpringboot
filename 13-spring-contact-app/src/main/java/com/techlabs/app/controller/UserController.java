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
import com.techlabs.app.dto.ContactRequestDto;
import com.techlabs.app.dto.ContactResponseDto;
import com.techlabs.app.dto.UserRequestDto;
import com.techlabs.app.dto.UserResponseDto;
import com.techlabs.app.service.ContactService;
import com.techlabs.app.Util.PagedResponse;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	ContactService contactService;
	


	public UserController(ContactService contactService) {
		super();
		this.contactService = contactService;
	}



	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "get all users")
	public ResponseEntity<PagedResponse<UserResponseDto>> getAllUsers(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size,
			@RequestParam(name = "sortBy", defaultValue = "userId") String sortBy,
			@RequestParam(name = "direction", defaultValue = "asc") String direction){
		return new ResponseEntity<PagedResponse<UserResponseDto>>(contactService.getAllUsers(page, size, sortBy, direction),HttpStatus.OK);
				
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "add a user")
	public ResponseEntity<UserResponseDto> addUser(@Valid @RequestBody UserRequestDto userRequestDto) {
	    return new ResponseEntity<>(contactService.addUser(userRequestDto), HttpStatus.ACCEPTED);
	}

	

	@PutMapping("/{user_id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "update user profile")
	public ResponseEntity<UserResponseDto> updateUserProfile( @RequestBody UserRequestDto userRequestDto,@PathVariable(name="user_id") long user_id) {

		return new ResponseEntity<UserResponseDto>(contactService.updateUserProfile(userRequestDto,user_id), HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "get user by id")
	public ResponseEntity<UserResponseDto> getUserById(@PathVariable(name="id") long id){
		return new ResponseEntity<UserResponseDto>(contactService.getUserId(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "delete user by id")
	public ResponseEntity<String> deleteUserById(@PathVariable(name="id") long id){
		return new ResponseEntity<String>(contactService.deleteUserId(id),HttpStatus.OK);
	}
	

}
