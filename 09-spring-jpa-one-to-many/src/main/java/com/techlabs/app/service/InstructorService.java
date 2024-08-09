package com.techlabs.app.service;

import java.util.List;

import com.techlabs.app.entity.Instructor;



public interface InstructorService {

	public Instructor saveInstructor(Instructor instructor);
	
	public List<Instructor> findAllInstructors();

	public Instructor findInstructorById(int id);

	public void deleteById(int id);

	public Instructor save(Instructor instructor);

	public Instructor addCourseToInstructor(int instructorId, int courseId);
	
}
