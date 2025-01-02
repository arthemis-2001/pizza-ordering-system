package com.artemtartakovsky.pizza_ordering_system.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerDTO {
	@JsonProperty("id")
	private long id;

	@JsonProperty(required = true)
	private String name;

	@JsonProperty(required = true)
	private String address;

	private List<OrderDTO> orders;

	public CustomerDTO() {

	}

	public CustomerDTO(long id, String name, String address, List<OrderDTO> orders) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.orders = orders;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<OrderDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDTO> orders) {
		this.orders = orders;
	}
}
