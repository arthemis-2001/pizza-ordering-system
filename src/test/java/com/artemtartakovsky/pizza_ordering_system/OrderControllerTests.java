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
class OrderControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testCreateOrder() throws Exception {
		String json = """
					{
						"customerId": 1,
						"pizzaOrders": [
							{ "pizzaId": 2, "quantity": 1 },
							{ "pizzaId": 3, "quantity": 1 }
						]
					}
				""";

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/orders").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());

		mockMvc.perform(MockMvcRequestBuilders.get("/api/orders")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
	}
}
