package com.cg.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dto.CustomerDTO;
import com.cg.entity.Customer;
import com.cg.exception.CustomerNotFoundException;
import com.cg.repository.CustomerRepo;


@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<CustomerDTO> getAllCustomers() {
		List<Customer> allCustomers = customerRepo.findAll();
		return allCustomers.stream().map(customer -> modelMapper.map(customer, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {

		return customerRepo.findById(id).map(customerEntity -> modelMapper.map(customerEntity, CustomerDTO.class))
				.orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@Override
	@Transactional
	public CustomerDTO addCustomer(CustomerDTO customerDTO) {
		Customer customerEntity = modelMapper.map(customerDTO, Customer.class);
		Customer savedCustomer = customerRepo.save(customerEntity);
		return modelMapper.map(savedCustomer, CustomerDTO.class);
	}

	/*
	@Override
	public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {

		return customerRepo.findById(id).map(customer -> {
			customer.setName(customerDTO.getName());
			customer.setEmail(customerDTO.getEmail());
			customer.setAddress(customerDTO.getAddress());
			return customerRepo.save(customer);
		}).map(cust -> modelMapper.map(cust, CustomerDTO.class)).orElseThrow(() -> new CustomerNotFoundException(id));

		
	}*/
	
	@Override
	@Transactional
	public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
	    return customerRepo.findById(id)
	    		.map(existingCustomer -> {
	            existingCustomer.setName(customerDTO.getName());
	            existingCustomer.setEmail(customerDTO.getEmail());
	            existingCustomer.setNumber(customerDTO.getNumber());
	            existingCustomer.setAddress(customerDTO.getAddress());
	            
	            // Save and return mapped DTO
	            Customer updatedCustomer = customerRepo.save(existingCustomer);
	            return modelMapper.map(updatedCustomer, CustomerDTO.class);
	        })
	        .orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@Override
	@Transactional
	public void deleteCustomer(Long id) {

		customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

		customerRepo.deleteById(id);

	}

}
