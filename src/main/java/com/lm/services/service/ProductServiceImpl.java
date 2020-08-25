/**
 * 
 */
package com.lm.services.service;

import java.util.List;
import java.util.Optional;

import com.lm.services.model.Product;
import com.lm.services.repository.ProductRepo;


/**
 * @author n1512378
 *
 */

public class ProductServiceImpl implements ProductService {
	
	protected ProductRepo productRepo =  ProductRepo.getInstance();

	
	@Override
	public List<Product> getProducts() {
		
		return productRepo.listAll();
	}

	@Override
	public Product createProduct(Product product) {
		
		return productRepo.add(product);
	}

	@Override
	public Optional < Product> retrieveProductById(int productId) {
		
		return productRepo.get(productId);
	}

	@Override
	public boolean deleteProductById(int productId) {
		
		return productRepo.delete(productId);
	}
	
	@Override
	public Product updateExistingProduct(Product product) {
		return productRepo.updateProduct(product);
	}

}
