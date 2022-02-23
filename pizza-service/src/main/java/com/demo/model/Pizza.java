package com.demo.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class Pizza {

	@Id
	@Column
	private String id;

	@Column
	private String name;

	@Column
	private int quantity;

	@Column
	private BigDecimal price;

}
