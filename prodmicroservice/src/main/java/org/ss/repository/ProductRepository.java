package org.ss.repository;

import org.ss.model.Product;

public interface ProductRepository {
	
	public Product getProduct(String id);

	public Product addProduct(Product product);

	public Product updateProduct(String id, Product product);
	

}
