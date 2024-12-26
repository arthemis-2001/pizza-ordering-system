package com.artemtartakovsky.pizza_ordering_system.beans;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "Name")
	private String name;

	@Column(name = "Address")
	private String address;

	@OneToMany(mappedBy = "customerId", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> items;

	public Customer(long id, List<Order> items, String name, String address) {
		super();
		this.id = id;
		this.items = items;
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Order> getItems() {
		return items;
	}

	public void setItems(List<Order> items) {
		this.items = items;
	}

}
