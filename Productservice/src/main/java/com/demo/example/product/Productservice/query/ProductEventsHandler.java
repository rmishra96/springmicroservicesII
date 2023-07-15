package com.demo.example.product.Productservice.query;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.demo.example.product.Productservice.core.data.ProductEntity;
import com.demo.example.product.Productservice.core.data.ProductReposistory;
import com.demo.example.product.Productservice.core.events.ProductCreatedEvent;

@Component
public class ProductEventsHandler {
	
	
	private final ProductReposistory productReposistory;
	
	public ProductEventsHandler(ProductReposistory productReposistory) {
		this.productReposistory = productReposistory;
	}
	
	
	@EventHandler
	public void on(ProductCreatedEvent event) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);
		productReposistory.save(productEntity);
	}
}
