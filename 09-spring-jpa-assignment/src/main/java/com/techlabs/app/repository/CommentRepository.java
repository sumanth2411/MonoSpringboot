package com.techlabs.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techlabs.app.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer>{

	
}
