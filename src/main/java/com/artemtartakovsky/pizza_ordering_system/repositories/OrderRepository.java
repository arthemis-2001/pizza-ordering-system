package com.artemtartakovsky.pizza_ordering_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemtartakovsky.pizza_ordering_system.beans.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
