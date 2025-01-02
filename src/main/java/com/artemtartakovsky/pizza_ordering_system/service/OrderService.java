package com.artemtartakovsky.pizza_ordering_system.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artemtartakovsky.pizza_ordering_system.dto.OrderDTO;
import com.artemtartakovsky.pizza_ordering_system.dto.PizzaOrderDTO;
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

	public List<OrderDTO> getAllOrders() {
		return orderRepository.findAll().stream().map(OrderService::convertToDTO).collect(Collectors.toList());
	}

	public Optional<OrderDTO> getOrderById(Long id) {
		return orderRepository.findById(id).map(OrderService::convertToDTO);
	}

	public OrderDTO createOrder(OrderDTO orderDTO) {
		long customerId = orderDTO.getCustomerId();
		Customer customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Customer not found with ID: " + customerId));

		Order order = new Order();
		order.setCustomer(customer);

		List<Long> pizzaIds = orderDTO.getPizzaOrders().stream().map(PizzaOrderDTO::getPizzaId)
				.collect(Collectors.toList());

		List<Integer> quantities = orderDTO.getPizzaOrders().stream().map(PizzaOrderDTO::getQuantity)
				.collect(Collectors.toList());

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

		return orderDTO;
	}

	public static OrderDTO convertToDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setId(order.getId());
		orderDTO.setCustomerId(order.getCustomer().getId());
		orderDTO.setPizzaOrders(
				order.getPizzaOrders().stream().map(OrderService::convertToDTO).collect(Collectors.toList()));
		return orderDTO;
	}

	public static PizzaOrderDTO convertToDTO(PizzaOrder pizzaOrder) {
		PizzaOrderDTO pizzaOrderDTO = new PizzaOrderDTO();
		pizzaOrderDTO.setId(pizzaOrder.getId());
		pizzaOrderDTO.setPizzaId(pizzaOrder.getPizza().getId());
		pizzaOrderDTO.setQuantity(pizzaOrder.getQuantity());
		return pizzaOrderDTO;
	}
}
