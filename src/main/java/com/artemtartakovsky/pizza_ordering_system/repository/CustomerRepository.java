package com.artemtartakovsky.pizza_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemtartakovsky.pizza_ordering_system.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
