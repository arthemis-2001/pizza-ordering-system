package com.artemtartakovsky.pizza_ordering_system.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Instant createdAt;

	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<PizzaOrder> pizzaOrders = new ArrayList<>();

	public Order() {

	}

	public Order(long id, Instant createdAt, Customer customer, List<PizzaOrder> pizzaOrders) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.customer = customer;
		this.pizzaOrders = pizzaOrders;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<PizzaOrder> getPizzaOrders() {
		return pizzaOrders;
	}

	public void setPizzaOrders(List<PizzaOrder> pizzaOrders) {
		this.pizzaOrders = pizzaOrders;
	}

	public void addPizzaOrder(PizzaOrder pizzaOrder) {
		this.pizzaOrders.add(pizzaOrder);
		pizzaOrder.setOrder(this);
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = Instant.now();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", createdAt=" + createdAt + ", customer=" + customer + ", pizzaOrders="
				+ pizzaOrders + "]";
	}
}
