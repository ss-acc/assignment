package org.ss.service;

import org.ss.model.Product;

public interface ProductService {
	
	public Product getProduct(String id);

	public Product addProduct(Product product);

	public Product updateProduct(String id, Product product);
	
}
