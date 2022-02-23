package com.demo.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.PizzaRequest;
import com.demo.model.Pizza;
import com.demo.repository.PizzaRepository;
import com.demo.service.PizzaService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Pizza Service implementation
 * 
 * @author Hema Preethi
 *
 */
@Service
public class PizzaServiceImpl implements PizzaService {

	/**
	 * Logger to log messages of {@link PizzaServiceImpl}
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaServiceImpl.class);

	/**
	 * Autowired bean of {@link ObjectMapper}
	 */
	@Autowired
	private ObjectMapper objectMapper;

	/**
	 * Autowired bean of {@link PizzaRepository}
	 */
	@Autowired
	private PizzaRepository pizzaRepository;

	@Override
	public String createPizza(PizzaRequest pizzaRequest) {
		try {
			LOGGER.info("Pizza Request received is: {}", pizzaRequest);
			Pizza pizza = objectMapper.convertValue(pizzaRequest, Pizza.class);
			pizza.setId(UUID.randomUUID().toString());
			pizzaRepository.save(pizza);
			LOGGER.info("Saved the pizza: {}", pizza);
			return "The Pizza is successfully created";
		} catch (IllegalArgumentException ex) {
			LOGGER.error("IllegalArgumentException in create pizza due to {}", ex.getMessage(), ex);
			throw new IllegalArgumentException(ex.getMessage());
		}
	}

}
