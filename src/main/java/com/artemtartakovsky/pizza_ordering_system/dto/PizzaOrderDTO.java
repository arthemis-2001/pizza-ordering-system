package com.artemtartakovsky.pizza_ordering_system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PizzaOrderDTO {
	@JsonProperty("id")
	private long id;

	@JsonProperty(required = true)
	private long pizzaId;

	@JsonProperty(required = true)
	private int quantity;

	public PizzaOrderDTO() {

	}

	public PizzaOrderDTO(long id, long pizzaId, int quantity) {
		super();
		this.id = id;
		this.pizzaId = pizzaId;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(long pizzaId) {
		this.pizzaId = pizzaId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
