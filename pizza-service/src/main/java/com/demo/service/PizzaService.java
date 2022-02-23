package com.demo.service;

import org.springframework.stereotype.Repository;

import com.demo.dto.PizzaRequest;

@Repository
public interface PizzaService {

	/**
	 * Service to create Pizza
	 * 
	 * @param pizzaRequest The object of {@link PizzaRequest}
	 * @return The response of create Pizza
	 */
	public String createPizza(PizzaRequest pizzaRequest);

}
