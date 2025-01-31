package com.techlabs.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.app.entity.Course;
import com.techlabs.app.entity.Student;
import com.techlabs.app.service.StudentService;
@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	 @Autowired
	 private StudentService courseService;
	 
	public StudentController(StudentService studentService,StudentService courseService) {
		super();
		this.studentService = studentService;
		this.courseService = courseService;
	}
	
    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }
    
    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
    
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
    
	 @PostMapping("/courses")
	 public Course createCourse(@RequestBody Course course) {
	        return courseService.createCourse(course);
	 }

	 @GetMapping("/courses")
	 public List<Course> getAllCourses() {
	        return courseService.getAllCourses();
	 }
	 
	    @GetMapping("/courses/{id}")
	    public Optional<Course> getCourseById(@PathVariable Long id) {
	        
	        return courseService.getCourseById(id);
	    }

	    @DeleteMapping("/courses/{id}")
	    public void deleteCourse(@PathVariable Long id) {
	        courseService.deleteCourse(id);
	    }
	    
	    @PostMapping("/{studentId}/courses/{courseId}")
	    public ResponseEntity<Student> addCourseToStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
	        Optional<Student> student = studentService.getStudentById(studentId);
	        Optional<Course> course = courseService.getCourseById(courseId);

	        if (student.isPresent() && course.isPresent()) {
	            student.get().getCourses().add(course.get());
	            studentService.createStudent(student.get());
	            return ResponseEntity.ok(student.get());
	        }
	        return ResponseEntity.notFound().build();
	    }
	    
	    @DeleteMapping("/{studentId}/courses/{courseId}")
	    public ResponseEntity<Student> removeCourseFromStudent(@PathVariable Long studentId, @PathVariable Long courseId) {
	        Optional<Student> student = studentService.getStudentById(studentId);
	        Optional<Course> course = courseService.getCourseById(courseId);

	        if (student.isPresent() && course.isPresent()) {
	            student.get().getCourses().remove(course.get());
	            studentService.createStudent(student.get());
	            return ResponseEntity.ok(student.get());
	        }
	        return ResponseEntity.notFound().build();
	    }

}
