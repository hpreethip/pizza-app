package com.demo.service;

import org.springframework.stereotype.Repository;

import com.demo.dto.PizzaRequest;

@Repository
public interface OrderService {

	String createOrder(PizzaRequest pizzaRequest);

}
