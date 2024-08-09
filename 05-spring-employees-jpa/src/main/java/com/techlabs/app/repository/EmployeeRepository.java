package com.techlabs.app.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techlabs.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
//	public Employee findByName(String name);
//	public List<Employee> findByActiveTrue();
//	public List<Employee> findByActiveFalse();
//	public List<Employee> findByNameStartingWith(String name);
//	public List<Employee> findBySalaryGreaterThanAndDesignation(double salary,String designation);
//	public List<Employee> findBySalaryBetween(Double start,Double end);
//	public int countByActiveTrue();
//	public int countByDesignation(String designation);
//	public int countBySalaryGreaterThan(double salary);
	  
	  @Query("select e from Employee e where e.name=?1")
	  public Employee findByName(String name);
	  
	  @Query("select e from Employee e where e.active=true")
	  public Page<Employee> findByActiveTrue(Pageable pageable);
	  
	  @Query("select e from Employee e where e.active=false")
	  public Page<Employee> findByActiveFalse(Pageable pageable);
	  
	  @Query("select e from Employee e where e.name like :name%")
	  public  Page<Employee> findByNameStartingWith(@Param("name")String prefix,Pageable pageable);
	  
	  @Query("select e from Employee e where e.salary>=?1 and designation=?2")
	  public Page<Employee> findBySalaryGreaterThanAndDesignation(double salary,String designation, Pageable pageable);
	 
	  @Query("select e from Employee e where e.salary between ?1 and ?2")
	  public Page<Employee> findBySalaryBetween(double startSalary,double endSalary, Pageable pageable);
	  
	  @Query("select count(e) from Employee e where e.active=true")
	  public Integer countByActiveTrue();
	  
	  @Query("select count(e) from Employee e where e.designation=?1")
	  public int countByDesignation(String designation);
	 
	  @Query("select count(e) from Employee e where e.salary>=?1 and active=true")
	  public int countBySalaryGreaterThan(double salary);
	  
	
	
//	  @Query(value="select * from employee where name=?1",nativeQuery=true)
//	  public Employee findByName(String name);
//	 
//	  @Query(value="select * from employee  where active=true",nativeQuery=true)
//	  public Page<Employee> findByActiveTrue(Pageable pageable);
//	  
//	  @Query(value="select * from employee  where active=false",nativeQuery=true)
//	  public Page<Employee> findByActiveFalse(Pageable pageable);
//	  
//	  @Query(value="select * from employee where name like :name%",nativeQuery=true)
//	  public  Page<Employee> findByNameStartingWith(@Param("name")String prefix,Pageable pageable);
//	  
//	  @Query(value="select * from employee e where e.salary>=?1 and designation=?2",nativeQuery=true)
//	  public Page<Employee> findBySalaryGreaterThanAndDesignation(double salary,String designation, Pageable pageable);
//	  
//	  @Query(value="select * from employee e where e.salary between ?1 and ?2",nativeQuery=true)
//	  public Page<Employee> findBySalaryBetween(double startSalary,double endSalary, Pageable pageable);
//	  
//	  @Query(value="select count(*) from employee e where e.active=true",nativeQuery=true)
//	  public Integer countByActiveTrue();
//	  
//	  @Query(value="select count(*) from employee e where e.designation=?1",nativeQuery=true)
//	  public int countByDesignation(String designation);
//	  
//	  @Query(value="select count(*) from employee e where e.salary>=?1 and active=true",nativeQuery=true)
//	  public int countBySalaryGreaterThan(double salary);
}
