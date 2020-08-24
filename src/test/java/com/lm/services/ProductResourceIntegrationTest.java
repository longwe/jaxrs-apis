package com.lm.services;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.lm.services.model.Product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

public class ProductResourceIntegrationTest {

    private HttpServer server;
    private WebTarget target;
    
    private static final String PRODUCT_RESOURCE_PATH = "v1/api/";

    @Before
    public void setUp() throws Exception {
        // start the server
        server = Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.getConfiguration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }
    @Test
    public void shouldFindAllProductsWithSucess()  throws Exception{
		final Response response = target.path
        (PRODUCT_RESOURCE_PATH+"products")
        .request(MediaType.APPLICATION_JSON_TYPE)
        .get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		
		final List<Product> result = response.readEntity(new GenericType<List<Product>>() {});
		assertNotNull(result);
    }

    @Test
	public void shouldCreateProductWithSucess() throws Exception {
		final Product product = new Product(4, "Macbook Pro", 1500);
		
		final Response response = target.path
		        (PRODUCT_RESOURCE_PATH+"products")
		        .request(MediaType.APPLICATION_JSON_TYPE)
		        .post((Entity.entity(product, MediaType.APPLICATION_JSON_TYPE)));

		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
    
    @Test
	public void shouldUpdateProductWithSucess() throws Exception {
		final Product product = new Product(2, "Macbook Pro", 1500);
		
		final Response response = target.path
		        (PRODUCT_RESOURCE_PATH+"products")
		        .request(MediaType.APPLICATION_JSON_TYPE)
		        .put((Entity.entity(product, MediaType.APPLICATION_JSON_TYPE)));
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void shouldFindProductByIdWithSucess() throws Exception {
	
		final Response response = target.path
		        (PRODUCT_RESOURCE_PATH+"products/2")
		        .request(MediaType.APPLICATION_JSON_TYPE)
		        .get();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	public void shouldDeleteProductByIdWithSucess() throws Exception {
	
		final Response response = target.path
		        (PRODUCT_RESOURCE_PATH+"products/2")
		        .request(MediaType.APPLICATION_JSON_TYPE)
		        .delete();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
   
   
}
