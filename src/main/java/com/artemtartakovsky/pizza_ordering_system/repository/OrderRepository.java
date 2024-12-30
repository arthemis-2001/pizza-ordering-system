package com.artemtartakovsky.pizza_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemtartakovsky.pizza_ordering_system.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
