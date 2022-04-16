package org.ss.common;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ProductDB {
	
	public static ProductDB productDB = null;

	public static Map<String, Object> map;

	private ProductDB() {
		map = new HashMap<String, Object>();

	}

	public static ProductDB getIntance() {
		if (productDB == null)
			productDB = new ProductDB();
		return productDB;

	}
	

}
