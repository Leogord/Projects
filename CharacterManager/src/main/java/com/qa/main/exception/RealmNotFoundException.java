package com.qa.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "No realm at that id")
public class RealmNotFoundException extends RuntimeException{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4680570176905426408L;

	public RealmNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}


}
