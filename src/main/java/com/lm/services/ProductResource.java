/**
 * 
 */
package com.lm.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;



import com.lm.services.model.Product;
import com.lm.services.service.ProductServiceImpl;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author n1512378
 *
 */
@Path("v1/api")
public class ProductResource {
	private final ProductServiceImpl productService;
	
	
	public ProductResource() {
		this.productService = new ProductServiceImpl();
	}
//	public ProductResource(ProductServiceImpl productService) {
//		super();
//		this.productService =  productService;
//	}
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/products")
	public List<Product> list() {
		return productService.getProducts();
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/products/old")
//	public Response add(Product product) throws URISyntaxException {
//		Product newProduct = productService.createProduct(product);
//		URI uri = new URI("/products/" + newProduct.getId());
//		return Response.created(uri).build();
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/products")
	public Product saveProduct(Product product) throws URISyntaxException {
		 
		return  productService.createProduct(product);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/products")
	public Product updateProduct( Product product) {
		return productService.updateExistingProduct(product);
		
	}
	
	@GET
	@Path("/products/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int productId) {
		Optional<Product> product = productService.retrieveProductById(productId);
		if (product != null) {
			return Response.ok(product, MediaType.APPLICATION_JSON).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
		
	@DELETE
	@Path("/products/{id}")
	public Response delete(@PathParam("id") int productId) {
		if (productService.deleteProductById(productId)) {
			return Response.ok().build();					
		} else {
			return Response.notModified().build();
		}
	}	
}