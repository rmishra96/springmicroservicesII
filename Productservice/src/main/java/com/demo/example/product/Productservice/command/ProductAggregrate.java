package com.demo.example.product.Productservice.command;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import com.demo.example.product.Productservice.core.events.ProductCreatedEvent;

@Aggregate
public class ProductAggregrate {
		
	@AggregateIdentifier
	private String productId;
	private String title;
	private BigDecimal price;
	private Integer quantity;
	
	public ProductAggregrate() {
		
	}
	
	@CommandHandler
	public ProductAggregrate(CreateProductCommand createProductCommand){
		// Validate Create Product Command
		
		if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <=0) {
			throw new IllegalArgumentException("Price cannnot be less or equal than zero");
		}
		
		if(createProductCommand.getTitle() == null || 
				createProductCommand.getTitle().isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty");
		}
		
		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
		
		BeanUtils.copyProperties(createProductCommand, productCreatedEvent);
		
		AggregateLifecycle.apply(productCreatedEvent);
	
	}
	
	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		this.productId = productCreatedEvent.getProductId();
		this.price = productCreatedEvent.getPrice();
		this.title = productCreatedEvent.getTitle();
		this.quantity = productCreatedEvent.getQuantity();
	}
	
	
	
}
