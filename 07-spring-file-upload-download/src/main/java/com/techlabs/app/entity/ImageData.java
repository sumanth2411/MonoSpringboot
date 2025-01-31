package com.techlabs.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Table(name = "image")
public class ImageData {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "id")
	  private int id;
	  
	  @Column(name = "name")
	  private String name;
	  
	  @Column(name = "type")
	  private String type;
	  
	  @Lob
	  @Column(name = "imagedata",length = 10000)
	  private byte[] imageData;
	
	  
	  public ImageData() {
		super();
	  }
	  
	  
	  public ImageData(int id, String name, String type, byte[] imageData) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.imageData = imageData;
	  }


	  public int getId() {
	    return id;
	  }
	  public void setId(int id) {
	    this.id = id;
	  }
	  public String getName() {
	    return name;
	  }
	  public void setName(String name) {
	    this.name = name;
	  }
	  public String getType() {
	    return type;
	  }
	  public void setType(String type) {
	    this.type = type;
	  }
	  public byte[] getImageData() {
	    return imageData;
	  }
	  public void setImageData(byte[] imageData) {
	    this.imageData = imageData;
	  }

	  
}
