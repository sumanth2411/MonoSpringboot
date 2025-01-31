package com.techlabs.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.techlabs.app.DTO.BlogDTO;
import com.techlabs.app.DTO.CommentDTO;
import com.techlabs.app.entity.Blog;
import com.techlabs.app.entity.Comment;
import com.techlabs.app.exception.BlogNotFoundException;
import com.techlabs.app.exception.CommentNotFoundException;
import com.techlabs.app.repository.BlogRepository;
import com.techlabs.app.repository.CommentRepository;
import com.techlabs.app.util.PagedResponse;

@Service
public class BlogServiceImpl implements BlogService{

	 private BlogRepository blogRepository;
	 
	 private CommentRepository commentRepository;
	 
	
	public BlogServiceImpl(BlogRepository blogRepository,CommentRepository commentRepository) {
		super();
		this.blogRepository = blogRepository;
		this.commentRepository = commentRepository;
	}


	@Override
	public PagedResponse<BlogDTO> getAllBlogs(int size, int page, String sortBy, String direction) {
		// TODO Auto-generated method stub
		 Sort sort = Sort.by(sortBy);
	        if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
	            sort = sort.descending();
	        } else {
	            sort = sort.ascending();
	        }
	        Pageable pageable = PageRequest.of(page, size, sort);
	        Page<Blog> blogPage = blogRepository.findAll(pageable);
	        List<BlogDTO> blogDTOs = convertBlogToBlogDTO(blogPage.getContent());
	        return new PagedResponse<>(blogDTOs, blogPage.getNumber(), blogPage.getSize(),
	                blogPage.getTotalElements(), blogPage.getTotalPages(), blogPage.isLast());
	}

	@Override
	public BlogDTO getBlogById(int id) {
		// TODO Auto-generated method stub
	    Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) {
            throw new BlogNotFoundException("Blog not found with the id: " + id);
        }
        return convertBlogToBlogDTO(blog);
	}

	@Override
	public BlogDTO saveandUpdateBlog(BlogDTO blogDTO) {
		// TODO Auto-generated method stub
	     Blog blog = convertBlogDTO_to_Blog(blogDTO);
	        if (blogDTO.getId() == 0) {
	            return convertBlogToBlogDTO(blogRepository.save(blog));
	        } else {
	            Blog Blog1 = blogRepository.findById(blog.getId()).orElse(null);
	            if (Blog1 != null) {
	                Blog1.setTitle(blog.getTitle());
	                Blog1.setCategory(blog.getCategory());
	                Blog1.setData(blog.getData());
	                Blog1.setPublishedDate(blog.getPublishedDate());
	                Blog1.setPublished(blog.isPublished());
	                Blog1.setComment(blog.getComment()); // Update comments
	                
	                if (Blog1.getComment() != null) {
	                    Blog1.getComment().clear();
	                    if (blog.getComment() != null) {
	                        for (Comment comment : blog.getComment()) {
	                            comment.setBlog(Blog1); 
	                            Blog1.getComment().add(comment);
	                        }
	                    }
	                } else {
	                    Blog1.setComment(blog.getComment());
	                }

	                return convertBlogToBlogDTO(blogRepository.save(Blog1));
	            }else {
	                throw new BlogNotFoundException("Blog not found with id: " + blogDTO.getId());
	            }
	            
	        }
	        
	}

    private BlogDTO convertBlogToBlogDTO(Blog blog) {
        BlogDTO blogDTO = new BlogDTO();
        blogDTO.setId(blog.getId());
        blogDTO.setTitle(blog.getTitle());
        blogDTO.setCategory(blog.getCategory());
        blogDTO.setData(blog.getData());
        blogDTO.setPublishedDate(blog.getPublishedDate());
        blogDTO.setPublished(blog.isPublished());
        blogDTO.setComments(convertCommentToCommentDTO(blog.getComment()));
        return blogDTO;
    }

    private List<BlogDTO> convertBlogToBlogDTO(List<Blog> blogs) {
        List<BlogDTO> blogDTOs = new ArrayList<>();
        for (Blog blog : blogs) {
            blogDTOs.add(convertBlogToBlogDTO(blog));
        }
        return blogDTOs;
    }

    private Blog convertBlogDTO_to_Blog(BlogDTO blogDTO) {
        Blog blog = new Blog();
        blog.setId(blogDTO.getId());
        blog.setTitle(blogDTO.getTitle());
        blog.setCategory(blogDTO.getCategory());
        blog.setData(blogDTO.getData());
        blog.setPublishedDate(blogDTO.getPublishedDate());
        blog.setPublished(blogDTO.isPublished());
        blog.setComment(convertDTOToComment(blogDTO.getComments(), blog)); // Set comments with the blog reference
        return blog;
    }

    private List<CommentDTO> convertCommentToCommentDTO(List<Comment> comments) {
        List<CommentDTO> commentDTOs = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setDescription(comment.getDescription());
            // Avoid recursive conversion by not setting BlogDTO in CommentDTO here
            commentDTOs.add(commentDTO);
        }
        return commentDTOs;
    }

    private List<Comment> convertDTOToComment(List<CommentDTO> commentDTOs, Blog blog) {
        List<Comment> comments = new ArrayList<>();
        for (CommentDTO commentDTO : commentDTOs) {
            Comment comment = new Comment();
            comment.setId(commentDTO.getId());
            comment.setDescription(commentDTO.getDescription());
            comment.setBlog(blog); // Set the blog reference
            comments.add(comment);
        }
        return comments;
    }
    
    @Override
    public String deleteBlogById(int id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) {
            throw new BlogNotFoundException("Blog not found with the id: " + id);
        }
        blogRepository.deleteById(id);
        return "deleted successfully";
    }

	@Override
	public BlogDTO addComment(int id, CommentDTO commentDTO) {
		Blog blog = blogRepository.findById(id).orElse(null);
		if(blog!=null) {
		Comment comment = convertCommentDTOToComment(commentDTO,blog);
		blog.addComment(comment);
		return convertBlogToBlogDTO(blogRepository.save(blog));
		}
		else {
			throw new BlogNotFoundException("Blog not found with the id: " + id);
		}
	}
	
	private Comment convertCommentDTOToComment(CommentDTO commentDTO,Blog blog) {
		Comment comment=new Comment();
		comment.setId(commentDTO.getId());
		comment.setDescription(commentDTO.getDescription());
		comment.setBlog(blog);
		return comment;
	}

	@Override
	public BlogDTO removeComment(int bid, int id) {
	    Blog blog = blogRepository.findById(bid).orElse(null);
	    if (blog != null) {
	        Comment comment = commentRepository.findById(id).orElse(null);
	        if (comment != null && blog.getComment().contains(comment)) {
	            blog.getComment().remove(comment);
	            blogRepository.save(blog);
	            commentRepository.delete(comment);
	            return convertBlogToBlogDTO(blog);
	        }
	        else {
	        	throw new CommentNotFoundException("comment not found with id:"+id);
	        }
	    }
	    else {
	    	throw new BlogNotFoundException("Blog not found with the id: " + bid);	
	  
	    }
	}
}
