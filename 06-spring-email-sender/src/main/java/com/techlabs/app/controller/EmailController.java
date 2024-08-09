package com.techlabs.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.app.entity.EmailStructure;
import com.techlabs.app.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
public class EmailController {

	 @Autowired
	  private EmailService mailService;
	  
	  
	  
	  @PostMapping("/send/{email}")
	  public String sendMail(@PathVariable (name="email") String mail, @RequestBody EmailStructure mailStructure) {
	    mailService.sendMail(mail, mailStructure);
	    return "mail sent Successfully";
	  }
	  
	  
	  @PostMapping("/sendWithAttachment/{email}")
	    public String sendEmailWithAttachment(@PathVariable(name = "email") String mail, @RequestBody EmailStructure mailStructure, @RequestParam(name = "path") String path) throws MessagingException {
	        if (mailService.sendEmailWithAttachment(mail, mailStructure, path)) {
	          return "mail sent Successfully";
	          
	        }
	    return "mail failed to send";
	        
	      
	}


}
