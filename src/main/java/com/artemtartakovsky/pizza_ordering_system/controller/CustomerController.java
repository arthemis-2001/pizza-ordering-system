package com.artemtartakovsky.pizza_ordering_system.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artemtartakovsky.pizza_ordering_system.dto.CustomerDTO;
import com.artemtartakovsky.pizza_ordering_system.model.Customer;
import com.artemtartakovsky.pizza_ordering_system.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public ResponseEntity<?> getAllCustomers() {
		return ResponseEntity.ok(customerService.getAllCustomers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
		Optional<CustomerDTO> customer = customerService.getCustomerById(id);
		return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerDTO customerDTO) {
		if (customerDTO.getName() == null || customerDTO.getAddress() == null) {
			return ResponseEntity.badRequest().body("Missing required fields: name and price");
		}

		try {
			Customer createdCustomer = customerService.createCustomer(customerDTO);
			URI location = URI.create("/api/customers/" + createdCustomer.getId());
			return ResponseEntity.created(location).body(createdCustomer);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body("Invalid JSON payload");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
		try {
			customerService.deleteCustomer(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
}
