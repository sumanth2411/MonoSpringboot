package com.techlabs.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeResponseDTO {

	@NotBlank
	@Size(min = 2, max = 50)
	private String name;


	@NotNull
	private String designation;

	@NotNull
	private boolean active;

	public EmployeeResponseDTO(String name, String email, String designation, boolean active) {
		super();
		this.name = name;
		this.designation = designation;
		this.active = active;
	}

	public EmployeeResponseDTO() {
		super();
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}


	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "EmployeeResponseDTO [name=" + name +", designation=" + designation + ", active="
				+ active + "]";
	}
	
	
}
