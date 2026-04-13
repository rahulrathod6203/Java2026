package com.cg.exception;

public class CustomerExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerExistsException(String email) {
		super("Customer already exists with the given email - " + email);
		// TODO Auto-generated constructor stub
	}

}
