package com.techlabs.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techlabs.app.entity.Course;
import com.techlabs.app.entity.Student;
import com.techlabs.app.repository.CourseRepository;
import com.techlabs.app.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
    private StudentRepository studentRepository;
	
	@Autowired
    private CourseRepository courseRepository;
	
	
	@Override
	public Student createStudent(Student student) {
		// TODO Auto-generated method stub
		return studentRepository.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id);
	}

	@Override
	public void deleteStudent(Long id) {
		// TODO Auto-generated method stub
		studentRepository.deleteById(id);
	}
	
	@Override
	public Course createCourse(Course course) {
		// TODO Auto-generated method stub
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}

	@Override
	public Optional<Course> getCourseById(Long id) {
		// TODO Auto-generated method stub
		return courseRepository.findById(id);
	}

	@Override
	public void deleteCourse(Long id) {
		// TODO Auto-generated method stub
		courseRepository.deleteById(id);
	}

	
}
