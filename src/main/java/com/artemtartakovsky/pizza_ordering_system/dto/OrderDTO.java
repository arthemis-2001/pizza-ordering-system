package com.artemtartakovsky.pizza_ordering_system.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDTO {
	@JsonProperty("id")
	private Long id;

	@JsonProperty("createdAt")
	private Instant createdAt;

	@JsonProperty(required = true)
	private Long customerId;

	@JsonProperty(required = true)
	private List<PizzaOrderDTO> pizzaOrders = new ArrayList<>();

	public OrderDTO() {

	}

	public OrderDTO(Long id, Long customerId, List<PizzaOrderDTO> pizzaOrders) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.pizzaOrders = pizzaOrders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<PizzaOrderDTO> getPizzaOrders() {
		return pizzaOrders;
	}

	public void setPizzaOrders(List<PizzaOrderDTO> pizzaOrders) {
		this.pizzaOrders = pizzaOrders;
	}
}
