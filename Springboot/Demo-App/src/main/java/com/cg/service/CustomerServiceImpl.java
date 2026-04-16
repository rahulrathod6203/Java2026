package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dto.CustomerDTO;
import com.cg.entity.Customer;
import com.cg.exception.CustomerExistsException;
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
		return customerRepo.findAll().stream().map(customer -> modelMapper.map(customer, CustomerDTO.class)).toList();
	}

	@Override
	public CustomerDTO getCustomerById(Long id) {

		return customerRepo.findById(id).map(customerEntity -> modelMapper.map(customerEntity, CustomerDTO.class))
				.orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@Override
	@Transactional
	public CustomerDTO addCustomer(CustomerDTO customerDTO) {

		if (customerRepo.existsByEmail(customerDTO.getEmail())) {
			throw new CustomerExistsException(customerDTO.getEmail());
		}

		Customer customerEntity = modelMapper.map(customerDTO, Customer.class);
		Customer savedCustomer = customerRepo.save(customerEntity);
		return modelMapper.map(savedCustomer, CustomerDTO.class);
	}

	@Override
	@Transactional
	public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
		return customerRepo.findById(id).map(existingCustomer -> {
			existingCustomer.setName(customerDTO.getName());
			existingCustomer.setEmail(customerDTO.getEmail());
			existingCustomer.setNumber(customerDTO.getNumber());
			existingCustomer.setAddress(customerDTO.getAddress());

			// Save and return mapped DTO
			Customer updatedCustomer = customerRepo.save(existingCustomer);
			return modelMapper.map(updatedCustomer, CustomerDTO.class);
		}).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	@Override
	@Transactional
	public void deleteCustomer(Long id) {

		customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));

		customerRepo.deleteById(id);

	}

//	@Override
//	public String authenticateCustomer(String email, String password) {
//
//		Optional<Customer> customer = customerRepo.findByEmail(email);
//
//		if (customer.isPresent()) {
//			if (customer.get().getPassword().equals(password)) {
//				return "Successfully Logged in!";
//			} else {
//				return "Invalid credentials!, please check your password!";
//			}
//		} else {
//			throw new CustomerNotFoundException(email);
//		}
//
//	}
	
	@Override
	public Boolean authenticateCustomer(String email, String password) {
	    Customer customer = customerRepo.findByEmail(email)
	            .orElseThrow(() -> new CustomerNotFoundException(""));

	    if (!customer.getPassword().equals(password)) {
	        // You could throw an exception or return false here
	        return false; 
	    }

	    return true;
	}

}
