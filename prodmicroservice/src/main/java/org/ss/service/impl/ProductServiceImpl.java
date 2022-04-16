package org.ss.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ss.model.Product;
import org.ss.repository.ProductRepository;
import org.ss.service.ProductService;

@Service("ProductService")

public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getProduct(String id) {

		return productRepository.getProduct(id);
	}

	@Override
	public Product addProduct(Product product) {
		productRepository.addProduct(product);
		return productRepository.getProduct(product.getid());
	}

	@Override
	public Product updateProduct(String id, Product product) {
		productRepository.updateProduct(id, product);

		return productRepository.getProduct(id);
	}


}
