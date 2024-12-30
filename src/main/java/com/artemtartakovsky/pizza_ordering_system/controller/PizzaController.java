package com.artemtartakovsky.pizza_ordering_system.controller;

import java.util.List;
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

import com.artemtartakovsky.pizza_ordering_system.model.Pizza;
import com.artemtartakovsky.pizza_ordering_system.service.PizzaService;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

	@Autowired
	public PizzaService pizzaService;

	@GetMapping
	public List<Pizza> getAllPizzas() {
		return pizzaService.getAllPizzas();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
		Optional<Pizza> pizza = pizzaService.getPizzaById(id);
		return pizza.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public Pizza createPizza(@RequestBody Pizza pizza) {
		return pizzaService.createPizza(pizza);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePizza(@PathVariable Long id) {
		pizzaService.deletePizza(id);
		return ResponseEntity.noContent().build();
	}
}
