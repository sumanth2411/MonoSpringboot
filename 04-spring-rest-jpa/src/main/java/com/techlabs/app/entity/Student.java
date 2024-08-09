package com.techlabs.app.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rollno")
	private int rollNo;
	
	@Column(name="name")
	private String name;
	
	@Column(name="percentage")
	private int percentage;
	
	
	public Student() {
		super();
	}


	public Student(int rollNo, String name, int percentage) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.percentage = percentage;
	}
	
	
	public Student(String name, int percentage) {
		super();
		this.name = name;
		this.percentage = percentage;
	}



	public int getRollNo() {
		return rollNo;
	}
	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	@Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", percentage=" + percentage + "]";
	}
	
	
}
