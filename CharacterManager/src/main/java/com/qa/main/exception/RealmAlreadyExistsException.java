package com.qa.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Realm already exists")
public class RealmAlreadyExistsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6764191088498985464L;

	public RealmAlreadyExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}


}
