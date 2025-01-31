package com.techlabs.app.service;

import java.util.List;

import com.techlabs.app.entity.Employee;

public interface EmployeeService {

	public Employee saveEmployee(Employee employee);
	
	public List<Employee> findAllEmployees();

	public Employee findById(int id);

	public void deleteById(int id);

	public void saveAndUpdate(Employee employee);
	
}
