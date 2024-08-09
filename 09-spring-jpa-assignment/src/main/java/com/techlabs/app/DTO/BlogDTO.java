package com.techlabs.app.DTO;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class BlogDTO {

	private int id;
	
	@NotBlank(message = "Title cant be blank.")
	private String title;
	
	@NotBlank(message = "Category cant be blank.")
	private String category;
	
	@NotBlank(message = "Data cant  be empty.")
	@Size(min = 10, max = 2000, message = "Data must be between 50 and 2000 characters.")
	private String data;

	@NotNull(message = "Published date must not be null.")
	@FutureOrPresent(message = "Published date must be in the present or future.")
	private LocalDateTime publishedDate = LocalDateTime.now();
	
	private boolean published;
	
	private List<CommentDTO> comments;

	public BlogDTO(int id, @NotBlank(message = "Title cant be blank.") String title,
			@NotBlank(message = "Category cant be blank.") String category,
			@NotBlank(message = "Data cant  be empty.") @Size(min = 50, max = 2000, message = "Data must be between 50 and 2000 characters.") String data,
			@NotNull(message = "Published date must not be null.") @FutureOrPresent(message = "Published date must be in the present or future.") LocalDateTime publishedDate,
			boolean published, List<CommentDTO> comments) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.data = data;
		this.publishedDate = publishedDate;
		this.published = published;
		this.comments = comments;
	}

	public BlogDTO() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public LocalDateTime getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(LocalDateTime publishedDate) {
		this.publishedDate = publishedDate;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "BlogDTO [id=" + id + ", title=" + title + ", category=" + category + ", data=" + data
				+ ", publishedDate=" + publishedDate + ", published=" + published + ", comments=" + comments + "]";
	}
	
	

}
