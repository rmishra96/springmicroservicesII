package com.demo.example.product.Productservice.core.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupReposistory extends JpaRepository<ProductLookupEntity, String> {
	
	ProductLookupEntity findByProductIdOrTitle(String productId, String title);
	
}
