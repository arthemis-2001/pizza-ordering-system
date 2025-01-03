package com.artemtartakovsky.pizza_ordering_system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CustomerControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllCustomers() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/customers")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
	}

	@Test
	void testGetCustomerById() throws Exception {
		long id = 1;

		mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("John Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.address").value("4 New Street"));
	}

	@Test
	void testGetCustomerByIdNotFound() throws Exception {
		long id = 99;

		mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testCreateCustomer() throws Exception {
		String json = """
					{
						"name": "Jane Doe",
						"address": "10 New Street"
					}
				""";

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/customers").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testCreateCustomerBadRequest() throws Exception {
		String json = """
					{
						"address": "10 New Street"
					}
				""";

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/customers").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void testDeleteCustomer() throws Exception {
		long id = 2;

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/customers/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	void testDeleteCustomerNotFound() throws Exception {
		long id = 99;

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/customers/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
