package com.techlabs.myapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employees")
public class Employees {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="empId")
	private int empId;
	
	@Column(name="empName")
	private String empName;
	
	@Column(name="empSalary")
	private int empSalary;
	
	
	public Employees(String empName, int empSalary) {
		super();
		this.empName = empName;
		this.empSalary = empSalary;
	}
	public Employees(int empId, String empName, int empSalary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getEmpSalary() {
		return empSalary;
	}
	public void setEmpSalary(int empSalary) {
		this.empSalary = empSalary;
	}
	@Override
	public String toString() {
		return "Employees [empId=" + empId + ", empName=" + empName + ", empSalary=" + empSalary + "]";
	}
	
	
}
