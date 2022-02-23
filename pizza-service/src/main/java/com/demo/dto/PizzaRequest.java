package com.demo.dto;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PizzaRequest {

	@NotNull
	private String name;

	@Min(1)
	private int quantity;

	@NotNull
	private BigDecimal price;

}
