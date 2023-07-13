package com.demo.example.product.Productservice.Rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private Environment env;

    @PostMapping
    public String createProduct(){
        return "HTTP POST is Handled";
    }


    @GetMapping
    public String getProducts(){
        return "HTTP Get request Handled";
    }


    @PutMapping
    public String updateProduct(){
        return "HTTP Put handled";
    }

    @DeleteMapping
    public String deleteProduct(){
        return "Http delete Handled";
    }
}
