package com.demo.example.product.Productservice.command;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import com.demo.example.product.Productservice.core.data.ProductLookupEntity;
import com.demo.example.product.Productservice.core.data.ProductLookupReposistory;
import com.demo.example.product.Productservice.core.events.ProductCreatedEvent;

@Component
@ProcessingGroup("product-group")
public class ProductLookupEventsHandler {
	
	private final ProductLookupReposistory productLookupReposistory;
	
	public ProductLookupEventsHandler(ProductLookupReposistory productLookupReposistory) {
		this.productLookupReposistory =productLookupReposistory;
	}
	
	@EventHandler
	public void on(ProductCreatedEvent event) {
		ProductLookupEntity productsLookUpEntity = new ProductLookupEntity(event.getProductId(),
				event.getTitle());
		
		productLookupReposistory.save(productsLookUpEntity);
	}
	
}
