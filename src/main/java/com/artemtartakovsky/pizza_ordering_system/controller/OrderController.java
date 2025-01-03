package com.artemtartakovsky.pizza_ordering_system.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artemtartakovsky.pizza_ordering_system.dto.OrderDTO;
import com.artemtartakovsky.pizza_ordering_system.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	public OrderService orderService;

	@GetMapping
	public ResponseEntity<?> getAllOrders() {
		return ResponseEntity.ok(orderService.getAllOrders());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable Long id) {
		Optional<OrderDTO> order = orderService.getOrderById(id);
		return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {
		OrderDTO createdOrder = orderService.createOrder(orderDTO);

		return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
	}
}
