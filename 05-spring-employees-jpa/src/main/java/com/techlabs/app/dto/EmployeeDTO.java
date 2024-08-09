package com.techlabs.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeDTO {

private long id;
	
	@NotBlank
	@Size(min = 2,max = 50)
	private String name;
	
	
	@NotNull
	private String designation;
	
	@NotNull
	private double salary;
	
	@NotNull
	private boolean active;

	public EmployeeDTO(long id, String name, String designation, double salary, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.designation = designation;
		this.salary = salary;
		this.active = active;
	}

	public EmployeeDTO() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [id=" + id + ", name=" + name + ", designation=" + designation
				+ ", salary=" + salary + ", active=" + active + "]";
	}
	
}
