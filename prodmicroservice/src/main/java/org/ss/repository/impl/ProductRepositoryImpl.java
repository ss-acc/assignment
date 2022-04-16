package org.ss.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.ss.common.ProductDB;
import org.ss.model.Product;
import org.ss.repository.ProductRepository;

@Repository("ProductRepository")
public class ProductRepositoryImpl implements ProductRepository {
	
	@Autowired
	private ProductDB productDB;

	@SuppressWarnings("static-access")
	@Override
	public Product getProduct(String id) {

		return (Product) productDB.getIntance().map.get(id);
	}

	@SuppressWarnings("static-access")
	@Override
	public Product addProduct(Product product) {

		return (Product) productDB.getIntance().map.put(product.getid(), product);
	}

	@SuppressWarnings("static-access")
	@Override
	public Product updateProduct(String id, Product product) {

		return (Product) productDB.getIntance().map.put(product.getid(), product);
	}

}
