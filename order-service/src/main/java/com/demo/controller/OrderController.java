package com.demo.controller;

import java.util.Objects;

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
import com.demo.service.OrderService;

/**
 * Order Controller for RESTApis
 * 
 * @author Hema Preethi
 *
 */
@RestController
public class OrderController {
	
	/**
	 * Logger to log messages of {@link OrderController}
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	/**
	 * Autowired bean of {@link OrderService}
	 */
	@Autowired
	private OrderService orderService;

	/**
	 * RestAPI to handle create Order
	 * 
	 * @param pizzaRequest The object of {@link OrderRequest}
	 * @return The response of create Order
	 * @throws Exception
	 */
	@PostMapping(value = RequestUri.ORDER, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> handleCreateOrder(@RequestBody PizzaRequest pizzaRequest) throws Exception {
		try {
			String response = null;
			if (pizzaRequest instanceof PizzaRequest) {
				/* Call to Create Order with the input request */
				response = orderService.createOrder(pizzaRequest);
				LOGGER.info("The result of Order response is : {}", response);
				if (Objects.nonNull(response))
					return new ResponseEntity<>(response, HttpStatus.CREATED);
				else
					return new ResponseEntity<>("Failed to create order", HttpStatus.INTERNAL_SERVER_ERROR);
			} else
				return new ResponseEntity<>("Input request is invalid", HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			LOGGER.error("Exception in create order due to {}", ex.getMessage(), ex);
			throw new Exception(ex.getMessage());
		}
	}

}
