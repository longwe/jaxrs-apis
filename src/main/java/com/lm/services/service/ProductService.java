/**
 * 
 */
package com.lm.services.service;

import java.util.List;
import java.util.Optional;

import com.lm.services.model.Product;

/**
 * @author n1512378
 *
 */
public interface ProductService {
	public List<Product> getProducts();
	
	public Product createProduct(Product product);
	
	public Optional<Product> retrieveProductById(int productId);
	
	public boolean deleteProductById (int productId);
	
	//public boolean updateProduct(Product product);
	
	public Product updateExistingProduct(Product product);
	
	

}
