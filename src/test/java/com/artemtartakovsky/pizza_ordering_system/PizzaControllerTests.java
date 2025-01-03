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
class PizzaControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllPizzas() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/pizzas")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}

	@Test
	void testGetPizzaById() throws Exception {
		long id = 1;

		mockMvc.perform(MockMvcRequestBuilders.get("/api/pizzas/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Margherita"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price").value(10.99));
	}

	@Test
	void testGetPizzaByIdNotFound() throws Exception {
		long id = 99;

		mockMvc.perform(MockMvcRequestBuilders.get("/api/pizzas/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void testCreatePizza() throws Exception {
		String json = """
					{
						"name": "Napolitana",
						"price": 13.99
					}
				""";

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/pizzas").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testCreatePizzaBadRequest() throws Exception {
		String json = """
					{
						"name": "Napolitana"
					}
				""";

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/pizzas").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

	@Test
	void testDeletePizza() throws Exception {
		long id = 2;

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/pizzas/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	void testDeletePizzaNotFound() throws Exception {
		long id = 99;

		mockMvc.perform(MockMvcRequestBuilders.delete("/api/pizzas/{id}", id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
