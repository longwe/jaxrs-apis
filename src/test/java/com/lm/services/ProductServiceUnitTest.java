package com.lm.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lm.services.model.Product;
import com.lm.services.repository.ProductRepo;
import com.lm.services.service.ProductServiceImpl;

/**
 * @author n1512378
 *
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ProductServiceUnitTest {

	@InjectMocks
	ProductServiceImpl productService;

	@Mock
	ProductRepo productRepo;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	// Some sanity check
	public void should_initialized_injectMocks() throws Exception {
		Assertions.assertNotNull(productService);
		Assertions.assertNotNull(productRepo);
		Assertions.assertNotNull(productRepo.getInstance());

	}

	@Test
	public void shouldFindAllProductsWithSucess() throws SQLException {
		//Given
		List<Product> productList=Arrays.asList(new Product(1, "Galaxy", 400), new Product(2, "Iphone", 1000));
	
		//When
		Mockito.when(productRepo.listAll())
				.thenReturn(productList);

		List<Product> expectedProductList = productService.getProducts();
		
		//Then
		Assertions.assertNotNull(expectedProductList);
		Assertions.assertEquals(2, expectedProductList.size());
	}

	@Test
	public void shouldCreateProductWithSucess() throws Exception {
		final Product product = new Product(4, "Macbook Pro", 1500);

		Mockito.when(productRepo.get(product.getId())).thenReturn(Optional.empty());

		Mockito.when(productRepo.add(product)).thenAnswer(invocation -> invocation.getArgument(0));

		Product createdProduct = productService.createProduct(product);

		Assertions.assertNotNull(createdProduct);

		verify(productRepo).add(createdProduct);

	}

	@Test
	public void shouldUpdateModifiedProductWithSucess() throws Exception {
		final Product product = new Product(2, "iPhone Pro", 1200);

		Mockito.when(productRepo.updateProduct(product)).thenReturn(product);

		final Product expectedProduct = productService.updateExistingProduct(product);
		
		Assertions.assertNotNull(expectedProduct);

		verify(productRepo).updateProduct(product);

	}

	@Test
	public void shouldRetrieveProductByIdWithSuccess() throws Exception {
		final Product product = new Product(10, "Iphone 8 Plus", 800);

		Mockito.when(productRepo.get(product.getId())).thenReturn(Optional.empty());
		
		final Optional <Product>exptectedProduct = productService.retrieveProductById(product.getId());
		Assertions.assertNotNull(exptectedProduct);
		verify(productRepo).get(product.getId());
	}
	
	@Test
	public void shouldDeletedProductByIdWithSuccess() throws Exception {
		final Product product = new Product(10, "Iphone 8 Plus", 800);

		Mockito.when(productRepo.delete(product.getId())).thenReturn(true);
		
		boolean hasBeenDeleted = productService.deleteProductById(product.getId());
		Assertions.assertTrue(hasBeenDeleted);
		verify(productRepo, times(1)).delete(product.getId());
	}

}
