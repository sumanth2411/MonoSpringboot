package com.techlabs.app.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	
	   @ExceptionHandler(BlogNotFoundException.class)
	    public ResponseEntity<BlogErrorResponse> handleBlogNotFoundException(BlogNotFoundException e) {
	        BlogErrorResponse error = new BlogErrorResponse();
	        error.setStatus(HttpStatus.NOT_FOUND.value());
	        error.setMessage(e.getMessage());
	        error.setTimeStamp(LocalDateTime.now());
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(CommentNotFoundException.class)
	    public ResponseEntity<CommentErrorResponse> handleCommentNotFoundException(CommentNotFoundException e) {
	        CommentErrorResponse error = new CommentErrorResponse();
	        error.setStatus(HttpStatus.NOT_FOUND.value());
	        error.setMessage(e.getMessage());
	        error.setTimeStamp(LocalDateTime.now());
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
	        ErrorResponse error = new ErrorResponse();
	        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        error.setMessage(e.getMessage());
	        error.setTimeStamp(LocalDateTime.now());
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	    
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
}
