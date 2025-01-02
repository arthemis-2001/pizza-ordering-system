package com.artemtartakovsky.pizza_ordering_system.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDTO {
	@JsonProperty("id")
	private long id;

	@JsonProperty(required = true)
	private long customerId;

	@JsonProperty(required = true)
	private List<PizzaOrderDTO> pizzaOrders;

	public OrderDTO() {

	}

	public OrderDTO(long id, long customerId, List<PizzaOrderDTO> pizzaOrders) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.pizzaOrders = pizzaOrders;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public List<PizzaOrderDTO> getPizzaOrders() {
		return pizzaOrders;
	}

	public void setPizzaOrders(List<PizzaOrderDTO> pizzaOrders) {
		this.pizzaOrders = pizzaOrders;
	}
}
