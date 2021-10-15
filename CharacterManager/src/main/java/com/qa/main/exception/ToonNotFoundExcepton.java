package com.qa.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No Character at that id")
public class ToonNotFoundExcepton extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6857144369113754786L;

	public ToonNotFoundExcepton() {
		super();
		// TODO Auto-generated constructor stub
	}


}
