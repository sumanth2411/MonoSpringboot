package com.techlabs.demoapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.demoapp.model.Instructor;

@RestController
public class InstructorController {

	
//	@Autowired
//	@Qualifier(value="javaInstructor")
	private Instructor instructor;
	
	public InstructorController(@Qualifier(value ="pythonInstructor") Instructor instructor) {
		super();
		this.instructor = instructor;
	}

//	@Autowired
//	@Qualifier(value="pythonInstructor")
//	public void setInstructor(Instructor instructor) {
//		this.instructor=instructor;
//	}
//	
	
	
	@GetMapping("/train-plan")
	public String getTrainingPlan() {
		return this.instructor.getTrainingPlan()+"<br>"+this.instructor.getResourceMaterial();
		
	}
		
	
}
