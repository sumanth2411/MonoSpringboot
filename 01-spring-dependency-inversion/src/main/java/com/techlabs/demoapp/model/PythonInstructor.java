package com.techlabs.demoapp.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PythonInstructor implements Instructor{

	public Resource resource;
	
	
	public PythonInstructor(@Qualifier(value="videoResource")Resource resource) {
		super();
		this.resource = resource;
	}


	@Override
	public String getTrainingPlan() {
		// TODO Auto-generated method stub
		return "prepare python topics";
	}
	
	



	public String getResourceMaterial() {
		// TODO Auto-generated method stub
		return this.resource.getResourceMaterial();
	}

}
