package com.artemtartakovsky.pizza_ordering_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemtartakovsky.pizza_ordering_system.model.Pizza;
import com.artemtartakovsky.pizza_ordering_system.repository.PizzaRepository;

@Service
public class PizzaService {
	private PizzaRepository pizzaRepository;

	@Autowired
	public PizzaService(PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}

	public List<Pizza> getAllPizzas() {
		return pizzaRepository.findAll();
	}

	public Optional<Pizza> getPizzaById(Long id) {
		return pizzaRepository.findById(id);
	}

	public Pizza createPizza(Pizza pizza) {
		return pizzaRepository.save(pizza);
	}

	public void deletePizza(Long id) {
		if (pizzaRepository.existsById(id)) {
			pizzaRepository.deleteById(id);
		} else {
			throw new RuntimeException("Pizza not found with id: " + id);
		}
	}
}
