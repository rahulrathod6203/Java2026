package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.dto.CustomerDTO;
import com.cg.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping
	public ResponseEntity<List<CustomerDTO>> showAllCustomers() {
		return new ResponseEntity<List<CustomerDTO>>(customerService.getAllCustomers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
		return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<CustomerDTO> addNewCustomer(@RequestBody CustomerDTO customerDTO) {
		return new ResponseEntity<>(customerService.addCustomer(customerDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDTO> updateExistingCustomer(@PathVariable Long id,
			@RequestBody CustomerDTO customerDTO) {
		return new ResponseEntity<>(customerService.updateCustomer(id, customerDTO), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return new ResponseEntity<>("Customer with id " + id + " deleted successfully!", HttpStatus.OK);
	}

}
