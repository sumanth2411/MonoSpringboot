package com.techlabs.app.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.techlabs.app.dto.EmployeeDTO;
import com.techlabs.app.dto.EmployeeResponseDTO;
import com.techlabs.app.entity.Employee;
import com.techlabs.app.util.PagedResponse;

import jakarta.validation.Valid;

public interface EmployeeService {

	PagedResponse<EmployeeResponseDTO> getAllEmployees(int page,int size,String sortBy,String direction);

	EmployeeDTO getEmployeeById(long id);

	EmployeeDTO saveAndUpdateEmployee(@Valid EmployeeDTO employeeDTO);

	void deleteEmployee(long id);

	EmployeeDTO getEmployeeByName(String name);

	PagedResponse<EmployeeDTO> getEmployeeByActiveTrue(int page,int size,String sortBy,String direction);
	
	PagedResponse<EmployeeDTO> getEmployeeByActiveFalse(int page,int size,String sortBy,String direction);


	PagedResponse<EmployeeDTO> getEmployeeNameStartingWith(String prefix,int page,int size,String sortBy,String direction);

	PagedResponse<EmployeeDTO> getEmployeeSalaryGreaterThanAndDepartment(double salary, String designation,int page,int size,String sortBy,String direction);

	PagedResponse<EmployeeDTO> getEmployeeSalaryBetween(Double startSalary, Double endSalary,int page,int size,String sortBy,String direction);

	int getEmployeeCountAndActive();

	int getEmployeeCountAndDesignation(String designation);

	int countSalaryGreaterthan(double salary);

	
	
}
