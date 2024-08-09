package com.techlabs.demoapp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.demoapp.model.Instructor;
import com.techlabs.demoapp.model.PdfResource;
import com.techlabs.demoapp.model.Resource;
import com.techlabs.demoapp.model.VideoResource;

@Configuration
public class InstructorConfig {
	   @Bean(name = "pdfResource")
	   @Primary
	    public Resource getPdfResourceObject() {
	    return new PdfResource();
	  }
	    
	    @Bean(name = "videoResource")
	    public Resource getVidoResourceObject() {
	    return new VideoResource();
	  }
	 
}
