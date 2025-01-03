package com.artemtartakovsky.pizza_ordering_system.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemtartakovsky.pizza_ordering_system.dto.CustomerDTO;
import com.artemtartakovsky.pizza_ordering_system.model.Customer;
import com.artemtartakovsky.pizza_ordering_system.repository.CustomerRepository;

@Service
public class CustomerService {
	private CustomerRepository customerRepository;

	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<CustomerDTO> getAllCustomers() {
		return customerRepository.findAll().stream().map(CustomerService::convertToDTO).collect(Collectors.toList());
	}

	public Optional<CustomerDTO> getCustomerById(Long id) {
		return customerRepository.findById(id).map(CustomerService::convertToDTO);
	}

	public Customer createCustomer(CustomerDTO customerDTO) {
		Customer customer = customerRepository.save(new Customer(customerDTO.getName(), customerDTO.getAddress()));
		return customer;
	}

	public void deleteCustomer(Long id) {
		if (customerRepository.existsById(id)) {
			customerRepository.deleteById(id);
		} else {
			throw new RuntimeException("Customer not found with id: " + id);
		}
	}

	public static CustomerDTO convertToDTO(Customer customer) {
		CustomerDTO customerDTO = new CustomerDTO();
		customerDTO.setId(customer.getId());
		customerDTO.setName(customer.getName());
		customerDTO.setAddress(customer.getAddress());
		customerDTO
				.setOrders(customer.getOrders().stream().map(OrderService::convertToDTO).collect(Collectors.toList()));
		return customerDTO;
	}
}
