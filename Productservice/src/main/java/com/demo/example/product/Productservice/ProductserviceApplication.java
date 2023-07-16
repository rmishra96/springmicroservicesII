package com.demo.example.product.Productservice;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.PropagatingErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.demo.example.product.Productservice.command.intercept.CreateProductCommandInterceptor;
import com.demo.example.product.Productservice.core.errorhandling.ProductServiceEventsErrorHandler;

@EnableDiscoveryClient
@SpringBootApplication
public class ProductserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductserviceApplication.class, args);
	}
	
	@Autowired
	public void registerCreateProductCommandInterceptor(ApplicationContext context,
			CommandBus commandbus) {
		
		commandbus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
		
	}
	
	@Autowired
	public void configure(EventProcessingConfigurer config) {
		config.registerListenerInvocationErrorHandler("product-group", conf -> 
				new ProductServiceEventsErrorHandler());
		
//		config.registerListenerInvocationErrorHandler("product-group", conf -> 
//					PropagatingErrorHandler.instance());
	}
	

}
