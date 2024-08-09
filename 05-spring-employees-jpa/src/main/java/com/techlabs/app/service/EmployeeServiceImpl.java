package com.techlabs.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.techlabs.app.dto.EmployeeDTO;
import com.techlabs.app.dto.EmployeeResponseDTO;
import com.techlabs.app.entity.Employee;
import com.techlabs.app.exception.EmployeeNotFoundException;
import com.techlabs.app.repository.EmployeeRepository;
import com.techlabs.app.util.PagedResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public PagedResponse<EmployeeResponseDTO> getAllEmployees(int page,int size,String sortBy,String direction) {
		Sort sort=direction.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(page, size,sort);
		
		Page<Employee> page1 = employeeRepository.findAll(pageable);
		List<Employee> employees = page1.getContent();
		
		if(employees.isEmpty()) {
			throw new EmployeeNotFoundException("No employee found add some");
		}
		 List<EmployeeResponseDTO> employeesDTO=convertEmployeeListToEmployeeResponseDTOList(page1.getContent());
		return new PagedResponse<EmployeeResponseDTO>(employeesDTO,page1.getNumber(),page1.getSize(),page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
	}

	@Override
	public EmployeeDTO getEmployeeById(long id) {
		Employee employee = employeeRepository.findById((int) id).orElse(null);
		if (employee == null) {
			throw new EmployeeNotFoundException("Employee with id " + id + " not found");
		}
		return 	convertEmployeeToEmployeeDto(employee);
	}

	private List<EmployeeResponseDTO> convertEmployeeListToEmployeeResponseDTOList(List<Employee> employees) {
		List<EmployeeResponseDTO> employeeList=new ArrayList<EmployeeResponseDTO>();
		for (Employee employee : employees) {
			EmployeeResponseDTO employeeResponseDTO =new EmployeeResponseDTO();
			employeeResponseDTO.setName(employee.getName());
			employeeResponseDTO.setDesignation(employee.getDesignation());
			employeeResponseDTO.setActive(employee.isActive());
			employeeList.add(employeeResponseDTO);
			
		}
		return employeeList;
	}

	@Override
	public EmployeeDTO saveAndUpdateEmployee(EmployeeDTO employeeDTO) {

		Employee newEmployee = convertEmployeeDTOToEmployee(employeeDTO);
		Employee save = null;
		if (newEmployee.getId() == 0) {
			newEmployee = employeeRepository.save(newEmployee);
			return convertEmployeeToEmployeeDto(newEmployee);
		}
		if (newEmployee.getId() != 0) {
			EmployeeDTO employeeById = getEmployeeById(newEmployee.getId());
			if (employeeById == null) {
				throw new EmployeeNotFoundException("Employee with id " + newEmployee.getId() + " not found");
			}
			save = employeeRepository.save(newEmployee);
		}
		return convertEmployeeToEmployeeDto(save);
	}
	


	private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		employee.setId(employeeDTO.getId());
		employee.setName(employeeDTO.getName());
		employee.setActive(employeeDTO.isActive());
		employee.setDesignation(employeeDTO.getDesignation());
		employee.setSalary(employeeDTO.getSalary());
		System.out.println(employee);
		return employee;
	}

	private EmployeeDTO convertEmployeeToEmployeeDto(Employee employee) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setId((int) employee.getId());
		employeeDTO.setName(employee.getName());
		employeeDTO.setDesignation(employee.getDesignation());
		employeeDTO.setActive(employee.isActive());
		employeeDTO.setSalary(employee.getSalary());
		return employeeDTO;
	}

	private List<EmployeeDTO> convertEmployeeListToEmployeeDtoList(List<Employee> employees) {
		List<EmployeeDTO> employeesDto = new ArrayList<EmployeeDTO>();
		for (Employee employee : employees) {
			EmployeeDTO dto = new EmployeeDTO();
			dto.setId((int) employee.getId());
			dto.setName(employee.getName());
			dto.setDesignation(employee.getDesignation());
			dto.setActive(employee.isActive());
			dto.setSalary(employee.getSalary());
			employeesDto.add(dto);
		}
		return employeesDto;

	}

	@Override
	public void deleteEmployee(long id) {
		EmployeeDTO employeeDTO=getEmployeeById(id);
		if(employeeDTO==null) {
			throw new EmployeeNotFoundException("Employee with id " + id + " not found");
		}
		Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
		employeeRepository.delete(employee);
		
	}

	@Override
	public EmployeeDTO getEmployeeByName(String name) {
		Employee employee = employeeRepository.findByName(name);
		if(employee==null) {
			throw new EmployeeNotFoundException("Employee with name "+name+" not found");
		}
		return convertEmployeeToEmployeeDto(employee);
	}

	@Override
	public PagedResponse<EmployeeDTO> getEmployeeByActiveTrue(int page,int size,String sortBy,String direction) {
		  Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		    Pageable pageable=PageRequest.of(page, size,sort);
		       Page<Employee> page1=employeeRepository.findByActiveTrue(pageable);
		
		List<Employee> employees = page1.getContent();
		if(employees.isEmpty()) {
			throw new EmployeeNotFoundException("No Employee is Active Currently");
		}
		//return convertEmployeeListToEmployeeDtoList(employees);
		  List<EmployeeDTO> employeesDTO= convertEmployeeListToEmployeeDtoList(page1.getContent());
		    return new PagedResponse<EmployeeDTO>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
	}

	@Override
	public PagedResponse<EmployeeDTO> getEmployeeByActiveFalse(int page,int size,String sortBy,String direction) {
		 Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		    Pageable pageable=PageRequest.of(page, size,sort);
		       Page<Employee> page1=employeeRepository.findByActiveFalse(pageable);
		       
		List<Employee> employees = page1.getContent();
		if(employees.isEmpty()) {
			throw new EmployeeNotFoundException("Employees are Active Currently");
		}
		 List<EmployeeDTO> employeesDTO= convertEmployeeListToEmployeeDtoList(page1.getContent());
		    return new PagedResponse<EmployeeDTO>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
	}

	@Override
	public PagedResponse<EmployeeDTO> getEmployeeNameStartingWith(String prefix,int page,int size,String sortBy,String direction) {
		  Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	      Pageable pageable=PageRequest.of(page, size,sort);
	         Page<Employee> page1=employeeRepository.findByNameStartingWith(prefix,pageable);
	         List<Employee> employees= page1.getContent();
		if(employees.isEmpty()) {
			throw new EmployeeNotFoundException("No Employee starts with "+prefix);
		}
		//return convertEmployeeListToEmployeeDtoList(employees);
		List<EmployeeDTO> employeesDTO=convertEmployeeListToEmployeeDtoList(page1.getContent());
	    return new PagedResponse<EmployeeDTO>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
	}

	@Override
	public PagedResponse<EmployeeDTO> getEmployeeSalaryGreaterThanAndDepartment(double salary, String designation,int page,int size,String sortBy,String direction) {
		//List<Employee> employees = employeeRepository.findBySalaryGreaterThanAndDesignation(salary, designation);
		 Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		    Pageable pageable=PageRequest.of(page, size,sort);
		     Page<Employee> page1=employeeRepository.findBySalaryGreaterThanAndDesignation(salary, designation,pageable);
		  
		     List<Employee> employees= page1.getContent();
		if(employees.isEmpty()) {
			throw new EmployeeNotFoundException("No Employee matches with the condition : 'salary greater than the '"+salary+"'designation'"+designation);
		}
		//return convertEmployeeListToEmployeeDtoList(employees);
		  List<EmployeeDTO> employeesDTO=convertEmployeeListToEmployeeDtoList(page1.getContent());
	      return new PagedResponse<EmployeeDTO>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
	}

	@Override
	public PagedResponse<EmployeeDTO> getEmployeeSalaryBetween(Double startSalary, Double endSalary,int page,int size,String sortBy,String direction) {
		//List<Employee> employees = employeeRepository.findBySalaryBetween(startSalary, endSalary);
		Sort sort = direction.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
	    Pageable pageable=PageRequest.of(page, size,sort);
	     Page<Employee> page1=employeeRepository.findBySalaryBetween(startSalary, endSalary,pageable);
	     List<Employee> employees= page1.getContent();
		if(employees.isEmpty()) {
			throw new EmployeeNotFoundException("No Employee matches with the condition : 'salary between '"+startSalary+" 'and' "+endSalary);
		}
		//return convertEmployeeListToEmployeeDtoList(employees);
		  List<EmployeeDTO> employeesDTO=convertEmployeeListToEmployeeDtoList(page1.getContent());
	      return new PagedResponse<EmployeeDTO>(employeesDTO, page1.getNumber(), page1.getSize(), page1.getTotalElements(),page1.getTotalPages(),page1.isLast());
	}

	@Override
	public int getEmployeeCountAndActive() {
		int count = employeeRepository.countByActiveTrue();
		if(count==0) {
			throw new EmployeeNotFoundException("No Employees active currently");
		}
		return count;
	}

	@Override
	public int getEmployeeCountAndDesignation(String designation) {
		int count = employeeRepository.countByDesignation(designation);
		if(count==0) {
			throw new EmployeeNotFoundException("No Employees with the designation "+designation);
		}
		return count;
	}

	@Override
	public int countSalaryGreaterthan(double salary) {
		int count = employeeRepository.countBySalaryGreaterThan(salary);
		if(count==0) {
			throw new EmployeeNotFoundException("No Employees salary greater than "+salary);
		}
		return count;
	}

}
