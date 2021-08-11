package cl.walmart.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import cl.walmart.model.ProductDO;
import cl.walmart.service.ProductService;

public class EmptiesControllerTest {

	ProductService productServiceMock = Mockito.mock(ProductService.class);
	
	@Autowired
	ProductController productController = new ProductController(productServiceMock);
	
	@BeforeEach
	void setUp() {
		Mockito.when(productServiceMock.find("")).thenReturn(new ArrayList<ProductDO>());
	}
	
	@DisplayName("Should send empty array of products")
	@Test
	void productShouldSendEmptyWhenNoData() {
		ResponseEntity<List<ProductDO>> response = productController.get("");
		System.out.println("Object returned:" + response.getBody());
		Assertions.assertEquals(true, response.getBody().isEmpty());
	}
}
