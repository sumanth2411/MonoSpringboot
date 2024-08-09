package com.techlabs.app.service;

import com.techlabs.app.DTO.BlogDTO;
import com.techlabs.app.DTO.CommentDTO;
import com.techlabs.app.util.PagedResponse;


public interface BlogService {

	PagedResponse<BlogDTO> getAllBlogs(int size, int page, String sortBy, String direction);

	public BlogDTO getBlogById(int id);

	
	 public BlogDTO saveandUpdateBlog(BlogDTO blogDTO);

	public String deleteBlogById(int id);

	public BlogDTO addComment(int id, CommentDTO commentDTO);

	public BlogDTO removeComment(int bid, int id);
}
