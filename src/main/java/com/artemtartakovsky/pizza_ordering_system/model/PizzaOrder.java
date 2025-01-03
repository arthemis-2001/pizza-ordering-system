package com.artemtartakovsky.pizza_ordering_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pizzas_orders")
public class PizzaOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne
	@JoinColumn(name = "pizza_id", nullable = false)
	private Pizza pizza;

	@Column(nullable = false)
	private Integer quantity;

	public PizzaOrder() {

	}

	public PizzaOrder(Long id, Order order, Pizza pizza, Integer quantity) {
		super();
		this.id = id;
		this.order = order;
		this.pizza = pizza;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Pizza getPizza() {
		return pizza;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		if (quantity > 0) {
			this.quantity = quantity;
		}
	}

	@Override
	public String toString() {
		return "PizzaOrder [id=" + id + ", order=" + order + ", pizza=" + pizza + ", quantity=" + quantity + "]";
	}
}
