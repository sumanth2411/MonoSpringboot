package com.techlabs.app.exception;

public class NoRecordFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NoRecordFoundException(String message) {
		super(message);
	}
}
