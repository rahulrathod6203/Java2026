package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
	
	Optional<Customer> findByEmail(String email);
	
	boolean existsByEmail(String email);

}
