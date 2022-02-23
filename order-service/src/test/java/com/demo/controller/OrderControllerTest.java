package com.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.constant.RequestUri;
import com.demo.dto.PizzaRequest;
import com.demo.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Order Controller for RESTApis
 * 
 * @author Hema Preethi
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
	
	/**
	 * Logger to log messages of {@link OrderControllerTest}
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderControllerTest.class);

	/**
	 * Autowired bean of {@link MockMvc}
	 */
	@Autowired
    private MockMvc mockMvc;
	
	/**
	 * Autowired bean of {@link ObjectMapper}
	 */
	@Autowired
    private ObjectMapper objectMapper;

	/**
	 * Autowired bean of {@link OrderService}
	 */
	@MockBean
	private OrderService orderService;

	/**
	 * Create Pizza order Positive test case using Mockito
	 */
	@Test
	void createOrderPizzaPositiveCase() {
		LOGGER.info("Create Pizza order Positive test case");
		try {
			PizzaRequest pizzaRequest = new PizzaRequest();
			pizzaRequest.setName("Margherita Pizza");
			pizzaRequest.setQuantity(2);
			pizzaRequest.setPrice(BigDecimal.valueOf(200));
			String requestBody = objectMapper.writeValueAsString(pizzaRequest);

			when(orderService.createOrder(ArgumentMatchers.any(PizzaRequest.class)))
					.thenReturn("The Pizza is successfully created");
			
			mockMvc.perform(post(RequestUri.ORDER)
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			        .andDo(print())
			        .andExpect(status().isCreated())
					.andExpect(content().string("The Pizza is successfully created"))
					.andReturn();
		} catch (Exception ex) {
			LOGGER.error("Exception in Create Pizza order Positive test case due to {}", ex.getMessage(), ex);
		}
	}
	
	/**
	 * Create Pizza order Negative test case using Mockito
	 */
	@Test
	void createOrderPizzaNegativeCase() {
		LOGGER.info("Create Pizza order Negative test case");
		try {
			PizzaRequest pizzaRequest = null;
			String requestBody = objectMapper.writeValueAsString(pizzaRequest);

			when(orderService.createOrder(ArgumentMatchers.any(PizzaRequest.class)))
					.thenReturn("The Pizza is successfully created");
			
			mockMvc.perform(post(RequestUri.ORDER)
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			        .andDo(print())
			        .andExpect(status().isBadRequest())
					.andReturn();
		} catch (Exception ex) {
			LOGGER.error("Exception in Create Pizza order Negative test case due to {}", ex.getMessage(), ex);
		}
	}

}
