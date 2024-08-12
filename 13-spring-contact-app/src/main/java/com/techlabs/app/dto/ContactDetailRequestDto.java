package com.techlabs.app.dto;

import com.techlabs.app.entity.ContactType;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactDetailRequestDto {

	
	private long contactDetailsId;
	private ContactRequestDto contact;
	@NotNull
	private ContactType contactType;
	 
}
