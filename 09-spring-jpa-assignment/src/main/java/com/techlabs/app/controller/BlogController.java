package com.techlabs.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techlabs.app.DTO.BlogDTO;
import com.techlabs.app.DTO.CommentDTO;
import com.techlabs.app.service.BlogService;
import com.techlabs.app.util.PagedResponse;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/blog")
public class BlogController {

	private BlogService blogService;

	public BlogController(BlogService blogService) {
		super();
		this.blogService = blogService;
	}
	
	
	@GetMapping
	public ResponseEntity<PagedResponse<BlogDTO>> getAllBlogs(@RequestParam(name = "size" , defaultValue = "5") int size, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "sortBy",defaultValue = "id") String sortBy, @RequestParam(name = "direction",defaultValue = "asc") String direction) {
		PagedResponse<BlogDTO> blogs=blogService.getAllBlogs(size,page,sortBy,direction);
		return new ResponseEntity<PagedResponse<BlogDTO>>(blogs,HttpStatus.OK);
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<BlogDTO> getBlogById(@PathVariable(name = "id")int id) {
		return new ResponseEntity<BlogDTO>(blogService.getBlogById(id),HttpStatus.OK); 
	}
	
	@PostMapping("/addblog")
	public ResponseEntity<BlogDTO> createABlog(@Valid @RequestBody BlogDTO blogDTO){
		return new ResponseEntity<BlogDTO>(blogService.saveandUpdateBlog(blogDTO),HttpStatus.ACCEPTED);
		
	}
	
	
	@PutMapping("/updateBlog")
	public ResponseEntity<BlogDTO> updateABlog(@Valid @RequestBody BlogDTO blogDTO){
	return new ResponseEntity<BlogDTO>(blogService.saveandUpdateBlog(blogDTO),HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/deleteBlog/{id}")
	public ResponseEntity<String> DeleteBlogId(@PathVariable(name="id")int id){
		String message=blogService.deleteBlogById(id);
		return new  ResponseEntity<String>(message,HttpStatus.OK);
	}

	@PutMapping("/addComment/{id}")
	public ResponseEntity<BlogDTO> addComment(@PathVariable(name = "id") int id,@Valid @RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<BlogDTO>(blogService.addComment(id,commentDTO),HttpStatus.OK);
	}


	@PutMapping("{bid}/{id}")
	public ResponseEntity<BlogDTO> removeComment(@PathVariable(name = "bid") int bid, @PathVariable(name = "id") int id) {
	    BlogDTO blogDTO = blogService.removeComment(bid, id);
	    if (blogDTO != null) {
	        return new ResponseEntity<>(blogDTO, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	

}
