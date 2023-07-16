package com.demo.example.product.Productservice.query;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.demo.example.product.Productservice.core.data.ProductEntity;
import com.demo.example.product.Productservice.core.data.ProductReposistory;
import com.demo.example.product.Productservice.core.events.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {
	
	
	private final ProductReposistory productReposistory;
	
	public ProductEventsHandler(ProductReposistory productReposistory) {
		this.productReposistory = productReposistory;
	}
	
	
	@ExceptionHandler(resultType = IllegalArgumentException.class)
	public void handle(IllegalArgumentException exception) {
		
	}
	
	@ExceptionHandler(resultType = Exception.class)
	public void handle(Exception exception) throws Exception {
		throw exception;
		
	}
	
	@EventHandler
	public void on(ProductCreatedEvent event) {
		ProductEntity productEntity = new ProductEntity();
		BeanUtils.copyProperties(event, productEntity);
		productReposistory.save(productEntity);
	}
}
