package com.cg.exception;

public class CustomerNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerNotFoundException(Long id) {
		super("Customer not found with the given id - " + id);
		// TODO Auto-generated constructor stub
	}

	public CustomerNotFoundException(String email) {
		super("Invalid email or email doesn't exist!");
		// TODO Auto-generated constructor stub
	}

}
