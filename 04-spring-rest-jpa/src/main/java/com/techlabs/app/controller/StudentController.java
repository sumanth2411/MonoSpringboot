package com.techlabs.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.app.entity.Student;
import com.techlabs.app.exception.StudentErrorResponse;
import com.techlabs.app.exception.StudentNotFoundException;
import com.techlabs.app.repository.StudentRepository;
import com.techlabs.app.service.StudentService;

@RestController
public class StudentController {

	private StudentService studentService;
	
	
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}



	@GetMapping("/students")
	public List<Student> getStudent() {
		List<Student> studentList=studentService.getAllStudents();
		return studentList;
	}
	
	@GetMapping("/students/{srollno}")
	public Student getStudentByRollno(@PathVariable(name="srollno")int rollno) {
		Student student = studentService.getStudentByRollno(rollno);
		if(student==null) {
			throw new StudentNotFoundException("Student with rollno "+rollno+" is not found");
		}
		return student;
	}
	
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		Student addedStudent = studentService.save(student);
		return addedStudent;
	}
	
	@PutMapping("/students")
	public Student updateStudent(@RequestBody Student student) {
		if(student.getRollNo()!=0) {
			Student updatedStudent = studentService.saveAndUpdate(student);
			return updatedStudent;
		}
		return null;
	}
	
	@DeleteMapping("students/{srollno}")
	 public String deleteStudent(@PathVariable(name = "srollno") int rollno) {
		studentService.deleteStudent(rollno);
	  return "deleted";
	  
	 }
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex){
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
		
	}
		
}
