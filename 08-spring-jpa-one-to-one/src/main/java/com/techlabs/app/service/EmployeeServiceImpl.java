package com.techlabs.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techlabs.app.entity.Employee;
import com.techlabs.app.repository.AddressRepository;
import com.techlabs.app.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	
	private AddressRepository addressRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository, AddressRepository addressRepository) {
		super();
		this.employeeRepository = employeeRepository;
		this.addressRepository = addressRepository;
	}
	
	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> findAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee findById(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).get();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);;
	}

	@Override
	public void saveAndUpdate(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepository.save(employee);
	}

	
	

	
	
}
