package com.artemtartakovsky.pizza_ordering_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artemtartakovsky.pizza_ordering_system.beans.Pizza;
import com.artemtartakovsky.pizza_ordering_system.repositories.CustomerRepository;
import com.artemtartakovsky.pizza_ordering_system.repositories.OrderRepository;
import com.artemtartakovsky.pizza_ordering_system.repositories.PizzaRepository;

@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	private PizzaRepository pizzaRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@GetMapping("/pizzas")
	public List<Pizza> getAllPizzas() {
		return pizzaRepository.findAll();
	}

	@PostMapping("/pizzas")
	public void createPizza(@RequestBody Pizza pizza) {
		pizzaRepository.save(pizza);
	}
}
