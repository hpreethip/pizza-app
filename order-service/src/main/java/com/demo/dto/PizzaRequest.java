package com.demo.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PizzaRequest {

	private String name;

	private int quantity;

	private BigDecimal price;

}
