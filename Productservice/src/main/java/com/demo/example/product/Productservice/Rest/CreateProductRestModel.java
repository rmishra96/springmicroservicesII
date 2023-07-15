package com.demo.example.product.Productservice.Rest;

import java.math.BigDecimal;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateProductRestModel {
	
	@NotBlank(message="Product title is required field")
	private String title;
	
	@Min(value=1, message="Price cannot be lower than 1")
	private BigDecimal price;
	
	@Min(value=1, message="Price cannot be lower than 1")
	@Max(value=5, message="Price cannot be lower than 10")
	private Integer quantity;
	
	
	
	
}