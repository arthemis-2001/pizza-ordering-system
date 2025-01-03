package com.artemtartakovsky.pizza_ordering_system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PizzaOrderDTO {
	@JsonProperty("id")
	private Long id;

	@JsonProperty(required = true)
	private Long pizzaId;

	@JsonProperty(required = true)
	private Integer quantity;

	public PizzaOrderDTO() {

	}

	public PizzaOrderDTO(Long id, Long pizzaId, Integer quantity) {
		super();
		this.id = id;
		this.pizzaId = pizzaId;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPizzaId() {
		return pizzaId;
	}

	public void setPizzaId(Long pizzaId) {
		this.pizzaId = pizzaId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		if (quantity > 0) {
			this.quantity = quantity;
		}
	}
}
