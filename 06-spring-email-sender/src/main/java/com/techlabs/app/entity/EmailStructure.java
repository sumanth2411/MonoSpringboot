package com.techlabs.app.entity;

public class EmailStructure {

	 private String Subject;
	  private String message;
	  public EmailStructure(String subject, String message) {
	    super();
	    Subject = subject;
	    this.message = message;
	  }
	  public String getSubject() {
	    return Subject;
	  }
	  public void setSubject(String subject) {
	    Subject = subject;
	  }
	  public String getMessage() {
	    return message;
	  }
	  public void setMessage(String message) {
	    this.message = message;
	  }
	  public EmailStructure() {
	    super();
	  }
	  

}
