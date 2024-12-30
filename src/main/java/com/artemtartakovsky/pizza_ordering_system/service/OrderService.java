package com.artemtartakovsky.pizza_ordering_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemtartakovsky.pizza_ordering_system.model.Customer;
import com.artemtartakovsky.pizza_ordering_system.model.Order;
import com.artemtartakovsky.pizza_ordering_system.model.Pizza;
import com.artemtartakovsky.pizza_ordering_system.model.PizzaOrder;
import com.artemtartakovsky.pizza_ordering_system.repository.CustomerRepository;
import com.artemtartakovsky.pizza_ordering_system.repository.OrderRepository;
import com.artemtartakovsky.pizza_ordering_system.repository.PizzaRepository;

@Service
public class OrderService {

	private CustomerRepository customerRepository;

	private OrderRepository orderRepository;

	private PizzaRepository pizzaRepository;

	@Autowired
	public OrderService(CustomerRepository customerRepository, OrderRepository orderRepository,
			PizzaRepository pizzaRepository) {
		super();
		this.customerRepository = customerRepository;
		this.orderRepository = orderRepository;
		this.pizzaRepository = pizzaRepository;
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	public Order createOrder(long customerId, List<Long> pizzaIds, List<Integer> quantities) {
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

		Order order = new Order();
		order.setCustomer(customer);

		for (int i = 0; i < pizzaIds.size(); i++) {
			long pizzaId = pizzaIds.get(i);
			int quantity = quantities.get(i);

			PizzaOrder pizzaOrder = new PizzaOrder();
			Pizza pizza = pizzaRepository.findById(pizzaId)
					.orElseThrow(() -> new RuntimeException("Pizza not found with ID: " + pizzaId));
			
			pizzaOrder.setPizza(pizza);
			pizzaOrder.setQuantity(quantity);
			
			order.addPizzaOrder(pizzaOrder);
		}

		customer.addOrder(order);

		customerRepository.save(customer);

		return order;
	}

	public void deletePizza(Long id) {
		if (orderRepository.existsById(id)) {
			orderRepository.deleteById(id);
		} else {
			throw new RuntimeException("Pizza not found with id: " + id);
		}
	}
}
