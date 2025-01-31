package com.techlabs.app.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CommentDTO {

	private int id;

	@NotBlank(message = "Description cant be blank.")
	@Size(min = 10, max = 2000, message = "Description must be between 50 and 2000 characters.")
	private String description;

	private BlogDTO blogDTO;

	public CommentDTO(int id,
			@NotBlank(message = "Description cant be blank.") @Size(min = 50, max = 200, message = "Description must be between 50 and 200 characters.") String description,
			BlogDTO blogDTO) {
		super();
		this.id = id;
		this.description = description;
		this.blogDTO = blogDTO;
	}

	public CommentDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BlogDTO getBlogDTO() {
		return blogDTO;
	}

	public void setBlogDTO(BlogDTO blogDTO) {
		this.blogDTO = blogDTO;
	}

	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", description=" + description + ", blogDTO=" + blogDTO + "]";
	}
	
	
	
}
