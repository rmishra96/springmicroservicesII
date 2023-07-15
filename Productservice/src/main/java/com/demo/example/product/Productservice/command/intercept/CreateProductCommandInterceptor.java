package com.demo.example.product.Productservice.command.intercept;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.BiFunction;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import com.demo.example.product.Productservice.command.CreateProductCommand;

import io.axoniq.axonserver.grpc.command.Command;

@Component
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

	@Override
	public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
			List<? extends CommandMessage<?>> messages) {
		// TODO Auto-generated method stub
		return (index , command )-> {
			if(CreateProductCommand.class.equals(command.getPayloadType())) {
				
				CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
				
				if(createProductCommand.getPrice().compareTo(BigDecimal.ZERO) <=0) {
					throw new IllegalArgumentException("Price cannnot be less or equal than zero");
				}
				
				if(createProductCommand.getTitle() == null || 
						createProductCommand.getTitle().isBlank()) {
					throw new IllegalArgumentException("Title cannot be empty");
				}
			}
			
			return command;
		};
	}
	
	
}
