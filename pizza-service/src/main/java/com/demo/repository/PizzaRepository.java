package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, String> {

}
