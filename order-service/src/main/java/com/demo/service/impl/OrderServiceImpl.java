package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.demo.constant.RequestUri;
import com.demo.dto.PizzaRequest;
import com.demo.service.OrderService;

/**
 * Order Service implementation
 * 
 * @author Hema Preethi
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	/**
	 * Logger to log messages of {@link OrderServiceImpl}
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	/**
	 * Pizza Service URL
	 */
	@Value("${pizza.service.url}")
	private String pizzaServiceUrl;
	
	/**
	 * Autowired bean of {@link RestTemplate}
	 */
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public String createOrder(PizzaRequest pizzaRequest) {
		LOGGER.info("Request to create Pizza order for: {}", pizzaRequest);
		String response = null;
		try {
			LOGGER.info("Pizza Service Url is: {}", pizzaServiceUrl);
			String createPizzaUrl = new StringBuilder().append(pizzaServiceUrl).append(RequestUri.PIZZA).toString();
			/* The Create Pizza HttpEntity with body */
			HttpEntity<PizzaRequest> requestHttpEntity = new HttpEntity<>(pizzaRequest);
			/* The Create Pizza RestTemplate exchange call */
			ResponseEntity<String> responseEntity = restTemplate.exchange(createPizzaUrl, HttpMethod.POST, requestHttpEntity, String.class);
			if (responseEntity instanceof ResponseEntity && responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
				response = responseEntity.getBody();
			}
			return response;
		} catch (RestClientException ex) {
			LOGGER.error("RestClientException in create order due to {}", ex.getMessage(), ex);
			throw new RestClientException(ex.getMessage());
		}
	}

}
