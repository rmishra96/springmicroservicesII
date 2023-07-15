package com.demo.example.product.Productservice.query;

import java.util.ArrayList;
import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.demo.example.product.Productservice.core.data.ProductEntity;
import com.demo.example.product.Productservice.core.data.ProductReposistory;
import com.demo.example.product.Productservice.query.rest.ProductRestModel;

@Component
public class ProductQueryHandler {
	
	private final ProductReposistory productReposistory;
	
	public ProductQueryHandler(ProductReposistory productReposistory) {
		this.productReposistory = productReposistory;
	}
	
	@QueryHandler
	public List<ProductRestModel> findProducts(FindProductsQuery query){
		List<ProductRestModel> productRests = new ArrayList<>();
		List<ProductEntity> storedProducts = productReposistory.findAll();
		
		for(ProductEntity productEntity : storedProducts) {
			ProductRestModel productRestModel = new ProductRestModel();
			BeanUtils.copyProperties(productEntity, productRestModel);
			productRests.add(productRestModel);
		}
		return productRests;
	}
}
