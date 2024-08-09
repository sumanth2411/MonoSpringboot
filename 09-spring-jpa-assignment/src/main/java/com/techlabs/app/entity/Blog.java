package com.techlabs.app.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="blog")
public class Blog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotBlank(message = "Title can't be blank.")
	private String title;
	
	@NotBlank(message = "Category can not be blank.")
	private String category;
	
	@NotBlank(message = "Data can not be blank.")
	@Size(min = 10, max = 2000, message = "Data must be between 50 and 2000 characters.")
	private String data;
	
	@NotNull(message = "Published date must not be null.")
	@FutureOrPresent(message = "Published date must be in the present or future.")
	private LocalDateTime publishedDate;
	
	private boolean published;
	
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},orphanRemoval = true,mappedBy = "blog")
	@JsonManagedReference
	private List<Comment> comment;

	
	

	public Blog(int id, @NotBlank(message = "Title can't be blank.") String title,
			@NotBlank(message = "Category can not be blank.") String category,
			@NotBlank(message = "Data can not be blank.") @Size(min = 50, max = 2000, message = "Data must be between 50 and 2000 characters.") String data,
			@NotNull(message = "Published date must not be null.") @FutureOrPresent(message = "Published date must be in the present or future.") LocalDateTime publishedDate,
			boolean published, List<Comment> comment) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.data = data;
		this.publishedDate = publishedDate;
		this.published = published;
		this.comment = comment;
	}


	public Blog() {
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

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", title=" + title + ", category=" + category + ", data=" + data + ", publishedDate="
				+ publishedDate + ", published=" + published + ", comment=" + comment + "]";
	}
	public void addComment(Comment comment2) {
		comment.add(comment2);

		
	}
	public void remove(Comment comment2) {
		comment.remove(comment2);
		
	}
	
	
}
