package com.artemtartakovsky.pizza_ordering_system;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.artemtartakovsky.pizza_ordering_system.controller.PizzaController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PizzaOrderingSystemApplicationTests {

	@Autowired
	private PizzaController controller;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertNotNull(controller);
	}
}
