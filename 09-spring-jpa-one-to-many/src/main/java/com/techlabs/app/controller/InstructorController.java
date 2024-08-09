package com.techlabs.app.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.app.entity.Instructor;
import com.techlabs.app.service.InstructorService;

@RestController
@RequestMapping("/instructors")
public class InstructorController {

	private InstructorService instructorService;
	
	public InstructorController(InstructorService instructorService) {
		super();
		this.instructorService = instructorService;
	}

	@GetMapping
	public List<Instructor> getAllInstructors(){
		return instructorService.findAllInstructors();
	}
	
	@PostMapping("/addInstructor")
	public Instructor addNewInstructor(@RequestBody Instructor instructor) {
		return instructorService.saveInstructor(instructor);
	}
	
	 @GetMapping("{id}")
	 public Instructor getInstructorById(@PathVariable(name="id")int id){
	    return instructorService.findInstructorById(id);
	  }
	 
	  @DeleteMapping("/delete-instructor/{id}")
	   public void deleteInstructorById(@PathVariable(name="id")int id) {
	     instructorService.deleteById(id);
	    }
	  
	  @PutMapping("/update-instructor")
	  public Instructor updateInstructor(@RequestBody Instructor instructor) {
	  
	    return instructorService.save(instructor);
	  }
	  
	  @PutMapping("/{instId}/course/{courseId}")
	  public Instructor addCourseToInstructor(@PathVariable(name = "instId")int instructorId,@PathVariable(name="courseId")int courseId) {
		  return instructorService.addCourseToInstructor(instructorId,courseId);
	  }
}
