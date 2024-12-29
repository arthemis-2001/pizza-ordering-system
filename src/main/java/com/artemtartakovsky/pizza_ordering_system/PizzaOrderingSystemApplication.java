package com.artemtartakovsky.pizza_ordering_system;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PizzaOrderingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzaOrderingSystemApplication.class, args);
	}

	@Bean
	public CommandLineRunner checkDatabaseConnection(DataSource dataSource) {
		return args -> {
			try {
				dataSource.getConnection().isValid(1);
				System.out.println("Server has successfully started and connected to the PostgreSQL database!");
			} catch (Exception e) {
				System.err.println("Failed to connect to the PostgreSQL database. Please check your configuration.");
				e.printStackTrace();
			}
		};
	}
}
