package org.ss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ss.model.Product;
import org.ss.service.ProductService;


@RestController
@RequestMapping("/api/v1")

public class CatalogController {
	
	
	@Autowired
    private ProductService productService;
	
	@RequestMapping("/")
    public String index() {
        return "<h1>Welcome to Product Catalog API!</h1>";
    }

   
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getproduct(@PathVariable("id") String id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @PutMapping(value = "/products") 
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
    }

    @PostMapping(value = "/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(id,product), HttpStatus.OK);
    }

   

}
