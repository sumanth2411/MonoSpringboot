package com.techlabs.app.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.techlabs.app.entity.EmailStructure;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	  @Autowired
	  private JavaMailSender mailSender;
	  
	  
	  @Value("$(spring.mail.username)")
	  private String fromMail ;
	  
	  
	  
	  public void sendMail(String mail,EmailStructure mailStructure) {
	      SimpleMailMessage simpleMailMessage =  new SimpleMailMessage();
	      simpleMailMessage.setFrom(fromMail);
	      simpleMailMessage.setSubject(mailStructure.getSubject());
	      simpleMailMessage.setText(mailStructure.getMessage());
	      simpleMailMessage.setTo(mail);
	      
	      
	      mailSender.send(simpleMailMessage);
	  }



	  public boolean sendEmailWithAttachment(String mail, EmailStructure mailStructure, String path) throws MessagingException {
	    
	          MimeMessage message = mailSender.createMimeMessage();
	          MimeMessageHelper helper = new MimeMessageHelper(message, true);
	          helper.setFrom(fromMail);
	          helper.setTo(mail);
	          helper.setSubject(mailStructure.getSubject());
	          helper.setText(mailStructure.getMessage());

	          File file = new File(path);
	          if (file.exists()) {
	              helper.addAttachment(file.getName(), file);
	          } 

	          mailSender.send(message);
	          return true; 
	      
	  }


}
