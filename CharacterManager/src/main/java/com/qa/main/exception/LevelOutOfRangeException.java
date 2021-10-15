package com.qa.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Level not in range, 1-60 only")
public class LevelOutOfRangeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1416090105684915435L;

	public LevelOutOfRangeException() {
		super();
		// TODO Auto-generated constructor stub
	}



}
