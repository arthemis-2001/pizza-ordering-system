package com.artemtartakovsky.pizza_ordering_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.artemtartakovsky.pizza_ordering_system.beans.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long> {

}
