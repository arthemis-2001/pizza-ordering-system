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

import com.artemtartakovsky.pizza_ordering_system.model.Pizza;
import com.artemtartakovsky.pizza_ordering_system.service.PizzaService;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

	@Autowired
	public PizzaService pizzaService;

	@GetMapping
	public ResponseEntity<?> getAllPizzas() {
		return ResponseEntity.ok(pizzaService.getAllPizzas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getPizzaById(@PathVariable Long id) {
		Optional<Pizza> pizza = pizzaService.getPizzaById(id);
		return pizza.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> createPizza(@RequestBody Pizza pizza) {
		if (pizza.getName() == null || pizza.getPrice() == null) {
			return ResponseEntity.badRequest().body("Missing required fields: name and price");
		}

		try {
			Pizza createdPizza = pizzaService.createPizza(pizza);
			URI location = URI.create("/api/pizzas/" + createdPizza.getId());
			return ResponseEntity.created(location).body(createdPizza);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body("Invalid JSON payload");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePizza(@PathVariable Long id) {
		try {
			pizzaService.deletePizza(id);
			return ResponseEntity.noContent().build();
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}
}
