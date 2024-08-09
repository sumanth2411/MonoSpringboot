package com.techlabs.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techlabs.app.entity.Course;
import com.techlabs.app.entity.Instructor;
import com.techlabs.app.repository.CourseRepository;
import com.techlabs.app.repository.InstructorRepository;

@Service
public class InstructorServiceImpl implements InstructorService{

	private InstructorRepository instructorRepository;
	private CourseRepository courseRepository;
	
	public InstructorServiceImpl(InstructorRepository instructorRepository, CourseRepository courseRepository) {
		super();
		this.instructorRepository = instructorRepository;
		this.courseRepository = courseRepository;
	}

	@Override
	public Instructor saveInstructor(Instructor instructor) {
		// TODO Auto-generated method stub
		return instructorRepository.save(instructor);
	}

	@Override
	public List<Instructor> findAllInstructors() {
		// TODO Auto-generated method stub
		return instructorRepository.findAll();
	}

	@Override
	public Instructor findInstructorById(int id) {
		// TODO Auto-generated method stub
		return instructorRepository.findById(id).get();
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		instructorRepository.deleteById(id);;
	}

	@Override
	public Instructor save(Instructor instructor) {
		// TODO Auto-generated method stub
		return instructorRepository.save(instructor);
	}

	@Override
	public Instructor addCourseToInstructor(int instructorId, int courseId) {
		// TODO Auto-generated method stub
		Instructor instructor = instructorRepository.findById(instructorId).orElse(null);
		if(instructor !=null) {
			Course course = courseRepository.findById(courseId).orElse(null);
			if(course !=null) {
				if(course.getInstructor() == null) {
					instructor.addCourse(course);
					course.setInstructor(instructor);
					instructorRepository.save(instructor);
					return instructor;
				}else {
					System.out.println("Instructor already assigned to the course");
				}
			}
		}
		
		
		return null;
	}

	
}
