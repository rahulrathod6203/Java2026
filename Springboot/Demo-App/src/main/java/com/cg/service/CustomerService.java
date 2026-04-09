package com.cg.service;

import java.util.List;

import com.cg.dto.CustomerDTO;
import com.cg.entity.Customer;

public interface CustomerService {
	
	List<CustomerDTO> getAllCustomers();
	
	CustomerDTO getCustomerById(Long id);
	
	CustomerDTO addCustomer(CustomerDTO customerDTO);
	
	CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
	
	void deleteCustomer(Long id);
}
