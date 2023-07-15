package com.demo.example.product.Productservice.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReposistory extends JpaRepository<ProductEntity, String> {
	
	ProductEntity findByProductId(String productId); 
	
	ProductEntity findByProductIdorTitle(String productId, String Title);

}
