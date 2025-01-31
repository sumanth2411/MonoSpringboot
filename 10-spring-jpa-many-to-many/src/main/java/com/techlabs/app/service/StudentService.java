package com.techlabs.app.service;

import java.util.List;
import java.util.Optional;

import com.techlabs.app.entity.Course;
import com.techlabs.app.entity.Student;

public interface StudentService {

	Student createStudent(Student student);

	List<Student> getAllStudents();

	Optional<Student> getStudentById(Long id);

	void deleteStudent(Long id);
	
	Course createCourse(Course course);

	List<Course> getAllCourses();

	Optional<Course> getCourseById(Long id);

	void deleteCourse(Long id);

	
}
