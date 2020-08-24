package com.lm.services.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.lm.services.model.Product;


public class ProductRepo {
	private static ProductRepo instance;
	private static List<Product> data = new ArrayList<Product>();
	
	static {
		data.add(new Product(1, "iPhone X", 999.99f));
		data.add(new Product(2, "XBOX 360", 329.50f));
	}
	
	private ProductRepo() {
		
	}
	
	public static ProductRepo getInstance() {
		if (instance == null) {
			instance = new ProductRepo();
		}
		
		return instance;				
	}
	
	public List<Product> listAll() {
		return new ArrayList<Product>(data);
	}
	
	public Product add(Product product) {
		int newId = data.size() + 1;
		product.setId(newId);
		data.add(product);
		
		return product;
	}
	
	public Optional <Product> get(int id) {
		Product productToFind = new Product(id);
		int index = data.indexOf(productToFind);
		if (index >= 0) {
			return Optional.ofNullable(data.get(index));
		}
		return null;
	}
	
	public boolean delete(int id) {
		boolean isSuccess = false;
		Product productToFind = new Product(id);
        int index = data.indexOf(productToFind);
        if (index >= 0) {
            data.remove(index);
            isSuccess = true;
        }
        
        return isSuccess;
    }
	
	
	
//	public boolean update(Product product) {
//	     boolean isSuccess = false;
//		int index = data.indexOf(product);
//		if (index >= 0) {
//			data.set(index, product);
//			isSuccess = true;
//		}
//		return isSuccess;
//	}
	
	public Product updateProduct(Product product) {
		Product aproduct = new Product();
		int index = data.indexOf(product);
		if (index >= 0) {
			data.set(index, product);
			aproduct = data.get(index);
		}
		return aproduct;
	}
}
