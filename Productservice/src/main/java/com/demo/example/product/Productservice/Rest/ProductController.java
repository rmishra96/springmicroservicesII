package com.demo.example.product.Productservice.Rest;

import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.example.product.Productservice.command.CreateProductCommand;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private Environment env;
	private final CommandGateway commandGateway;

	@Autowired
	public ProductController(Environment env, CommandGateway commandGateway) {
		this.env = env;
		this.commandGateway = commandGateway;
	}

	@PostMapping
	public String createProduct(@RequestBody CreateProductRestModel createProductRestModel) {

		CreateProductCommand createProductCommand = CreateProductCommand.builder()
				.price(createProductRestModel.getPrice()).quantity(createProductRestModel.getQuantity())
				.title(createProductRestModel.getTitle()).productId(UUID.randomUUID().toString()).build();

		String returnValue;

		returnValue = commandGateway.sendAndWait(createProductCommand);

		return returnValue;
	}

	@GetMapping
	public String getProducts() {
		return "HTTP Get request Handled" + env.getProperty("local.server.port");
	}

	@PutMapping
	public String updateProduct() {
		return "HTTP Put handled";
	}

	@DeleteMapping
	public String deleteProduct() {
		return "Http delete Handled";
	}
}
