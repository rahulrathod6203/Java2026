package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CustomerDTO;
import com.cg.service.CustomerService;

@RestController
@RequestMapping("/api/v1/auth")
public class LoginRegisterController {

	@Autowired
	CustomerService customerService;

	@GetMapping
	public ResponseEntity<String> login(@RequestBody CustomerDTO customerDTO) {

		String authenticatedCustomer = customerService.authenticateCustomer(customerDTO.getEmail(),
				customerDTO.getPassword());

		return new ResponseEntity<String>(authenticatedCustomer, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> register(@RequestBody CustomerDTO customerDTO) {

		customerService.addCustomer(customerDTO);
		return new ResponseEntity<>(("Successfully registered!"), HttpStatus.CREATED);
	}

}
