package com.techlabs.demoapp.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JavaInstructor implements Instructor {

	public Resource resource;
	
	public JavaInstructor(@Qualifier(value="pdfResource")Resource resource) {
		super();
		this.resource = resource;
	}



	@Override
	public String getTrainingPlan() {
		// TODO Auto-generated method stub
		return "learn oops concepts";
	}


	public String getResourceMaterial() {
		// TODO Auto-generated method stub
		return this.resource.getResourceMaterial();
	}
	

}
