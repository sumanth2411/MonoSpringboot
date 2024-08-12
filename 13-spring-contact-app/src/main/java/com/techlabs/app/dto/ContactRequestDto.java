package com.techlabs.app.dto;

import java.util.List;

import com.techlabs.app.entity.ContactDetails;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactRequestDto {

	private long contactId;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotNull
	private boolean active;
	
	private List<ContactDetails> contactDetails;
	
	private UserRequestDto user;
	
}
