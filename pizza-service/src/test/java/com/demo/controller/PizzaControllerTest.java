package com.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.demo.constant.RequestUri;
import com.demo.dto.PizzaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PizzaControllerTest {
	
	/**
	 * Logger to log messages of {@link PizzaControllerTest}
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(PizzaControllerTest.class);
	
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
     * Create Pizza Positive test case using Mockito
     */
	@Test
    void createPizzaPositiveCase() {
		LOGGER.info("Create Pizza Positive test case");
		try {
			PizzaRequest pizzaRequest = new PizzaRequest();
			pizzaRequest.setName("Margherita Pizza");
			pizzaRequest.setQuantity(2);
			pizzaRequest.setPrice(BigDecimal.valueOf(200));
			String requestBody = objectMapper.writeValueAsString(pizzaRequest);
			
			mockMvc.perform(post(RequestUri.PIZZA)
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			        .andDo(print())
			        .andExpect(status().isCreated())
					.andExpect(content().string("The Pizza is successfully created"))
					.andReturn();
		} catch (Exception ex) {
			LOGGER.error("Exception in Create Pizza Positive test case due to {}", ex.getMessage(), ex);
		}
	}
	
	/**
	 * Create Pizza Negative test case using Mockito
	 */
	@Test
    void createPizzaNegativeCase() {
		LOGGER.info("Create Pizza Negative test case");
		try {
			PizzaRequest pizzaRequest = new PizzaRequest();
			pizzaRequest.setQuantity(2);
			pizzaRequest.setPrice(BigDecimal.valueOf(200));
			String requestBody = objectMapper.writeValueAsString(pizzaRequest);
			
			mockMvc.perform(post(RequestUri.PIZZA)
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			        .andDo(print())
			        .andExpect(status().isBadRequest())
					.andReturn();
		} catch (Exception ex) {
			LOGGER.error("Exception in Create Pizza Negative test case due to {}", ex.getMessage(), ex);
		}
	
	}

	/**
	 * Create Pizza null test case using Mockito
	 */
	@Test
    void createPizzaNullCase() {
		LOGGER.info("Create Pizza null test case");
		try {
			PizzaRequest pizzaRequest = null;
			String requestBody = objectMapper.writeValueAsString(pizzaRequest);
			
			mockMvc.perform(post(RequestUri.PIZZA)
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			        .andDo(print())
			        .andExpect(status().isBadRequest())
					.andReturn();
		} catch (Exception ex) {
			LOGGER.error("Exception in Create Pizza null test case due to {}", ex.getMessage(), ex);
		}
	}

	
}
