package com.demo.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.constant.RequestUri;
import com.demo.dto.PizzaRequest;
import com.demo.service.PizzaService;

/**
 * Pizza Controller for RESTApis
 * 
 * @author Hema Preethi
 *
 */
@RestController
public class PizzaController {

	/**
	 * Logger to log messages of {@link PizzaController}
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaController.class);

	/**
	 * Autowired bean of {@link PizzaService}
	 */
	@Autowired
	private PizzaService pizzaService;

	/**
	 * RestAPI to handle create Pizza
	 * 
	 * @param pizzaRequest The object of {@link PizzaRequest}
	 * @return The response of create Pizza
	 * @throws Exception
	 */
	@PostMapping(value = RequestUri.PIZZA, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> handleCreatePizza(@RequestBody @Valid PizzaRequest pizzaRequest) throws Exception {
		try {
			String response = null;
			if (pizzaRequest instanceof PizzaRequest) {
				/* Call to Create Pizza with the input request */
				response = pizzaService.createPizza(pizzaRequest);
				LOGGER.info("The result of Pizza response is : {}", response);
				if (Objects.nonNull(response))
					return new ResponseEntity<>(response, HttpStatus.CREATED);
				else
					return new ResponseEntity<>("Failed to create pizza", HttpStatus.INTERNAL_SERVER_ERROR);
			} else
				return new ResponseEntity<>("Input request is invalid", HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			LOGGER.error("Exception in create pizza due to {}", ex.getMessage(), ex);
			throw new Exception(ex.getMessage());
		}
	}

}
