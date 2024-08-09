package com.techlabs.myapp.dao;

import java.util.List;

import com.techlabs.myapp.entity.Student;

public interface StudentDao {

	public void save(Student student);
	
	public List<Student> getAllStudents();
	
	//public Student getStudentByRollno(int i);

	public Student getStudentByRollno(int i);

	public List<Student> getStudentByName(String name);

	public void updateStudent(Student s);

	public void deleteStudent(int rollNo);

	public void updateStudentQuery(Student s);

	
}
